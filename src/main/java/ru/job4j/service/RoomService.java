package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.dto.RoomDTO;
import ru.job4j.domain.Room;
import ru.job4j.exception.AlreadyExistException;
import ru.job4j.exception.AuthorizationException;
import ru.job4j.exception.EmptyArgumentException;
import ru.job4j.repository.RoomRepository;

import javax.servlet.http.HttpServletRequest;

import static ru.job4j.Util.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoomService {

    private final RoomRepository rooms;
    private final PersonService persons;

    public RoomService(RoomRepository rooms, PersonService persons) {
        this.rooms = rooms;
        this.persons = persons;
    }

    public Room save(Room room, HttpServletRequest request) {
        String user = getUserFromToken(request);
        if (rooms.existsByName(room.getName())) {
            throw new AlreadyExistException("Комната");
        }
        room.setOwner(user);
        return rooms.save(room);
    }

    public void delete(int id, HttpServletRequest request) {
        String user = getUserFromToken(request);
        boolean isAdmin = persons.getByName(user).getRole().getName().equals("ROLE_ADMIN");
        Room persisted = getById(id);
         if (!(user.equals(persisted.getOwner()) || isAdmin)) {
            throw new AuthorizationException("Удаление комнаты");
        }
        rooms.deleteById(id);
    }

    public void update(RoomDTO roomDTO, HttpServletRequest request) {
        String user = getUserFromToken(request);
        boolean isAdmin = persons.getByName(user).getRole().getName().equals("ROLE_ADMIN");
        Room persisted = getById(roomDTO.getId());
        if (roomDTO.getName().isEmpty()) {
            throw new EmptyArgumentException("Название комнаты");
        }
        if (rooms.existsByName(roomDTO.getName())) {
            throw new AlreadyExistException("Комната");
        }
        if (!(user.equals(persisted.getOwner()) || isAdmin)) {
            throw new AuthorizationException("Переименование комнаты");
        }
        persisted.setName(roomDTO.getName());
        rooms.save(persisted);
    }

    public List<Room> getAll() {
        return StreamSupport.stream(rooms.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Room getById(int id) {
        return rooms.findById(id).orElseThrow(() -> new NoSuchElementException("Комната не найдена"));
    }

}
