package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Message;
import ru.job4j.domain.Room;
import ru.job4j.repository.MessageRepository;
import ru.job4j.repository.RoomRepository;
import static ru.job4j.Util.*;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messages;

    @Autowired
    private RoomRepository rooms;

    public Room save(Message message, int id) {
        Room result = null;
        Room room = rooms.findById(id).orElse(null);
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        user = user.equals("anonymousUser") ? null : user;
        if (room != null && user != null) {
            message.setAuthor(user);
            room.addMessage(message);
            result = rooms.save(room);
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Message persisted = messages.findById(id).orElse(null);
        if (persisted != null && (user.equals(persisted.getAuthor()) || isAdmin())) {
            messages.deleteById(id);
            result = true;
        }
        return result;
    }
}
