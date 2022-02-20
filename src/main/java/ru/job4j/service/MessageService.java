package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Message;
import ru.job4j.domain.Room;
import ru.job4j.repository.MessageRepository;
import ru.job4j.repository.PersonRepository;
import ru.job4j.repository.RoomRepository;

import javax.servlet.http.HttpServletRequest;

import static ru.job4j.Util.*;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messages;

    @Autowired
    private RoomRepository rooms;

    @Autowired
    private PersonRepository persons;

    public Room save(Message message, int id, HttpServletRequest request) {
        Room result = null;
        Room room = rooms.findById(id).orElse(null);
        String user = getUserFromToken(request);
        if (room != null) {
            message.setAuthor(user);
            room.addMessage(message);
            result = rooms.save(room);
        }
        return result;
    }

    public boolean delete(int id, HttpServletRequest request) {
        boolean result = false;
        String user = getUserFromToken(request);
        boolean isAdmin = persons.findByName(user).getRole().getName().equals("ROLE_ADMIN");
        Message persisted = messages.findById(id).orElse(null);
        if (persisted != null && (user.equals(persisted.getAuthor()) || isAdmin)) {
            messages.deleteById(id);
            result = true;
        }
        return result;
    }
}
