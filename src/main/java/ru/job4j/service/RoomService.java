package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Room;
import ru.job4j.repository.PersonRepository;
import ru.job4j.repository.RoomRepository;

import javax.servlet.http.HttpServletRequest;

import static ru.job4j.Util.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoomService {

    @Autowired
    private RoomRepository rooms;

    @Autowired
    private PersonRepository persons;

    public Room save(Room room, HttpServletRequest request) {
        Room result = null;
        String user = getUserFromToken(request);
        if (!rooms.existsByName(room.getName())) {
            room.setOwner(user);
            result = rooms.save(room);
        }
        return result;
    }

    public boolean deleteById(int id, HttpServletRequest request) {
        boolean result = false;
        String user = getUserFromToken(request);
        boolean isAdmin = persons.findByName(user).getRole().getName().equals("ROLE_ADMIN");
        Room persisted = rooms.findById(id).orElse(null);
        if (persisted != null && (persisted.getOwner().equals(user) || isAdmin)) {
            rooms.deleteById(id);
            result = true;
        }
        return result;
    }

    public boolean update(Room room, HttpServletRequest request) {
        boolean result = false;
        String user = getUserFromToken(request);
        boolean isAdmin = persons.findByName(user).getRole().getName().equals("ROLE_ADMIN");
        Room persisted = rooms.findById(room.getId()).orElse(null);
        if (persisted != null && (persisted.getOwner().equals(user) || isAdmin)) {
            persisted.setName(room.getName());
            rooms.save(persisted);
            result = true;
        }
        return result;
    }

    public List<Room> getAll() {
        return StreamSupport.stream(rooms.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Room getById(int id) {
        return rooms.findById(id).orElse(null);
    }

}
