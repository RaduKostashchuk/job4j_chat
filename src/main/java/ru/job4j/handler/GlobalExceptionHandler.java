package ru.job4j.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.job4j.exception.AlreadyExistException;
import ru.job4j.exception.AuthorizationException;
import ru.job4j.exception.EmptyArgumentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(value = {EmptyArgumentException.class})
    public void handleException1(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", "Обязательные поля не заполнены:");
            put("details", e.getMessage());
        }}));
    }

    @ExceptionHandler(value = {AuthorizationException.class})
    public void handleException2(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", "Нет прав доступа на операцию:");
            put("details", e.getMessage());
        }}));
    }

    @ExceptionHandler(value = {AlreadyExistException.class})
    public void handleException3(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", "Такой объект уже существует:");
            put("details", e.getMessage());
        }}));
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public void handleException4(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
        }}));
    }
}
