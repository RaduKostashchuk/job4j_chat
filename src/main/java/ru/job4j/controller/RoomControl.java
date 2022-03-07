package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Room;
import ru.job4j.dto.RoomDTO;
import ru.job4j.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@RequestMapping("/room/")
public class RoomControl {
    private final RoomService service;

    public RoomControl(RoomService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<RoomDTO> getAll() {
        return service.getAll().stream().map(RoomDTO::of).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable @Positive(message = "Номер комнаты должен быть > 0") int id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Room> create(@Valid @RequestBody Room room, HttpServletRequest request) {
        return new ResponseEntity<>(service.save(room, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive(message = "Номер комнаты должен быть > 0") int id,
                                       HttpServletRequest request) {
        service.delete(id, request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/")
    public ResponseEntity<Void> update(@Valid @RequestBody RoomDTO roomDTO, HttpServletRequest request) {
        service.update(roomDTO, request);
        return ResponseEntity.ok().build();
    }
}
