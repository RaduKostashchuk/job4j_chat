package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Room;
import ru.job4j.repository.RoomRepository;
import static ru.job4j.Util.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoomService {

    @Autowired
    private RoomRepository rooms;

    public Room save(Room room) {
        Room result = null;
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        user = user.equals("anonymousUser") ? null : user;
        if (user != null && !rooms.existsByName(room.getName())) {
            room.setOwner(user);
            result = rooms.save(room);
        }
        return result;
    }

    public boolean deleteById(int id) {
        boolean result = false;
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Room persisted = rooms.findById(id).orElse(null);
        if (persisted != null && (persisted.getOwner().equals(user) || isAdmin())) {
            rooms.deleteById(id);
            result = true;
        }
        return result;
    }

    public boolean update(Room room) {
        boolean result = false;
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Room persisted = rooms.findById(room.getId()).orElse(null);
        if (persisted != null && (persisted.getOwner().equals(user) || isAdmin())) {
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
