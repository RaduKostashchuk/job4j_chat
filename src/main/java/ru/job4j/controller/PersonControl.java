package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Person;
import ru.job4j.model.PersonModel;
import ru.job4j.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person/")
public class PersonControl {

    @Autowired
    private PersonService service;

    @PostMapping("/")
    public ResponseEntity<PersonModel> create(@RequestBody Person person) {
        person = service.save(person);
        return person != null
                ? new ResponseEntity<>(PersonModel.of(person), HttpStatus.CREATED)
                :  ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping("/")
    public List<PersonModel> getAll() {
        return service.getAll().stream().map(PersonModel::of).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonModel> getById(@PathVariable int id) {
        Person person = service.getById(id);
        return person != null
                ? new ResponseEntity<>(PersonModel.of(person), HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
