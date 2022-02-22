package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Room;
import ru.job4j.dto.RoomDTO;
import ru.job4j.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
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
    public ResponseEntity<Room> getById(@PathVariable int id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Room> create(@RequestBody Room room, HttpServletRequest request) {
        return new ResponseEntity<>(service.save(room, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id, HttpServletRequest request) {
        service.delete(id, request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/name/")
    public ResponseEntity<Void> update(@RequestBody RoomDTO roomDTO, HttpServletRequest request) {
        service.update(roomDTO, request);
        return ResponseEntity.ok().build();
    }
}
