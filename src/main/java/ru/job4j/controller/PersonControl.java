package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Person;
import ru.job4j.exception.RegistrationException;
import ru.job4j.dto.PersonDTO;
import ru.job4j.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
public class PersonControl {

    private final PersonService service;

    public PersonControl(PersonService service) {
        this.service = service;
    }

    @PostMapping("/reg")
    public ResponseEntity<PersonDTO> create(@Valid @RequestBody Person person) {
        person = service.save(person);
        return new ResponseEntity<>(PersonDTO.of(person), HttpStatus.CREATED);
    }

    @PatchMapping("/edit/")
    public ResponseEntity<Void> update(@Valid @RequestBody Person person, HttpServletRequest request) {
        service.update(person, request);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(value = {RegistrationException.class})
    public ResponseEntity<?> exceptionHandler(RegistrationException e) {
        return ResponseEntity
                .badRequest()
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                .body(List.of(Map.of("message", e.getMessage())));

    }
}
