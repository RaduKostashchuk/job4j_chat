package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Message;
import ru.job4j.service.MessageService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/message/")
public class MessageControl {

    @Autowired
    private MessageService service;

    @PostMapping("/{id}")
    public ResponseEntity<Void> save(@RequestBody Message message, @PathVariable int id, HttpServletRequest request) {
        return  service.save(message, id, request) != null
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> save(@PathVariable int id, HttpServletRequest request) {
        return  service.delete(id, request)
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
