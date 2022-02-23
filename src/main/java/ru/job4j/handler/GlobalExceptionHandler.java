package ru.job4j.handler;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.job4j.exception.AlreadyExistException;
import ru.job4j.exception.AuthorizationException;

import javax.validation.ConstraintViolationException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {AuthorizationException.class})
    public ResponseEntity<?> handleException1(Exception e) {
        return ResponseEntity
            .badRequest()
            .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
            .body(List.of(Map.of("message", "Нет прав доступа на операцию:", "details", e.getMessage())));
    }

    @ExceptionHandler(value = {AlreadyExistException.class})
    public ResponseEntity<?> handleException2(Exception e) {
        return ResponseEntity
                .badRequest()
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                .body(List.of(Map.of("message", "Такой объект уже существует:", "details", e.getMessage())));
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<?> handleException3(Exception e) {
        return ResponseEntity
                .badRequest()
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                .body(List.of(Map.of("message", e.getMessage())));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleException4(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(
                e.getFieldErrors().stream()
                        .map(f -> Map.of(
                                "message",
                                String.format("%s. Текущее значение: %s", f.getDefaultMessage(), f.getRejectedValue())
                        ))
                        .collect(Collectors.toList()));
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<?> handleException5(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(
                e.getConstraintViolations().stream()
                        .map(violation -> Map.of(
                                "message",
                                violation.getMessage()
                        )
        ));
    }
}
