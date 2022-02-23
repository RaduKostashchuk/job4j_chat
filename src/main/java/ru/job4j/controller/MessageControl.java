package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Message;
import ru.job4j.service.MessageService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Controller
@Validated
@RequestMapping("/message/")
public class MessageControl {

    private final MessageService service;

    public MessageControl(MessageService service) {
        this.service = service;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> save(@Valid @RequestBody Message message,
                                     @PathVariable("id") @Positive(message = "Номер комнаты должен быть > 0") int roomId,
                                     HttpServletRequest request) {
        service.save(message, roomId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Message message,
                                       @PathVariable @Positive(message = "Номер сообщения должен быть > 0") int id,
                                       HttpServletRequest request) {
        service.update(message, id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> save(@PathVariable @Positive(message = "Номер сообщения должен быть > 0") int id,
                                     HttpServletRequest request) {
        service.delete(id, request);
        return ResponseEntity.ok().build();
    }
}
