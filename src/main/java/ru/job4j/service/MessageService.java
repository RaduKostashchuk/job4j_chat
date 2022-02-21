package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.Message;
import ru.job4j.domain.Room;
import ru.job4j.exception.AuthorizationException;
import ru.job4j.exception.EmptyArgumentException;
import ru.job4j.repository.MessageRepository;
import ru.job4j.repository.PersonRepository;
import ru.job4j.repository.RoomRepository;

import javax.servlet.http.HttpServletRequest;

import java.util.NoSuchElementException;

import static ru.job4j.Util.*;

@Service
public class MessageService {

    private final MessageRepository messages;
    private final RoomRepository rooms;
    private final PersonRepository persons;

    public MessageService(MessageRepository messages, RoomRepository rooms, PersonRepository persons) {
        this.messages = messages;
        this.rooms = rooms;
        this.persons = persons;
    }

    public void save(Message message, int id, HttpServletRequest request) {
        Room room = rooms.findById(id).orElse(null);
        String user = getUserFromToken(request);
        if (message.getContent().isEmpty()) {
            throw new EmptyArgumentException("Текст сообщения");
        }
        if (room == null) {
            throw new EmptyArgumentException("Номер комнаты");
        }
        message.setAuthor(user);
        room.addMessage(message);
        rooms.save(room);
    }

    public void delete(int id, HttpServletRequest request) {
        String user = getUserFromToken(request);
        boolean isAdmin = persons.findByName(user).getRole().getName().equals("ROLE_ADMIN");
        Message persisted = findById(id);
        if (!(user.equals(persisted.getAuthor()) || isAdmin)) {
            throw new AuthorizationException("Удаление сообщения");
        }
        messages.deleteById(id);
    }

    public Message findById(int id) {
        return messages.findById(id).orElseThrow(() -> new NoSuchElementException("Сообщение не найдено"));
    }
}
