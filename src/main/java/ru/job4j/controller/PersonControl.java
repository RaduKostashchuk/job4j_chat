package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Person;
import ru.job4j.exception.RegistrationException;
import ru.job4j.dto.PersonModel;
import ru.job4j.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/person")
public class PersonControl {

    private final PersonService service;
    private final ObjectMapper objectMapper;

    public PersonControl(PersonService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/reg")
    public ResponseEntity<PersonModel> create(@RequestBody Person person) {
        person = service.save(person);
        return new ResponseEntity<>(PersonModel.of(person), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonModel> getById(@PathVariable int id) {
        Person person = service.getById(id);
        return new ResponseEntity<>(PersonModel.of(person), HttpStatus.OK);
    }

    @PatchMapping("/edit/")
    public ResponseEntity<Void> update(@RequestBody Person person, HttpServletRequest request) {
        service.update(person, request);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(value = {RegistrationException.class})
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {{
            put("message", e.getMessage());
        }}));
    }
}
