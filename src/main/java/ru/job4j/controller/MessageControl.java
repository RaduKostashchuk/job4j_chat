package ru.job4j.controller;

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

    private final MessageService service;

    public MessageControl(MessageService service) {
        this.service = service;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> save(@RequestBody Message message, @PathVariable int id, HttpServletRequest request) {
        service.save(message, id, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> save(@PathVariable int id, HttpServletRequest request) {
        service.delete(id, request);
        return ResponseEntity.ok().build();
    }
}
