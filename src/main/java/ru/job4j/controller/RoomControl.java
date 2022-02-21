package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Room;
import ru.job4j.model.RoomModel;
import ru.job4j.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/room/")
public class RoomControl {

    @Autowired
    private RoomService service;

    @GetMapping("/")
    public List<RoomModel> getAll() {
        return service.getAll().stream().map(RoomModel::of).toList();
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

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Room room, HttpServletRequest request) {
        service.update(room, request);
        return ResponseEntity.ok().build();
    }
}
