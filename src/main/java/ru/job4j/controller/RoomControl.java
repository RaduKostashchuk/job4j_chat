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
        room = service.save(room, request);
        return room != null
                ? new ResponseEntity<>(room, HttpStatus.CREATED)
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id, HttpServletRequest request) {
        return service.deleteById(id, request)
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Room room, HttpServletRequest request) {
        return service.update(room, request)
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
