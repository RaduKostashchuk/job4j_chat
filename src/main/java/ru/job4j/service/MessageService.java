package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.Message;
import ru.job4j.domain.Room;
import ru.job4j.exception.AuthorizationException;
import ru.job4j.exception.EmptyArgumentException;
import ru.job4j.repository.MessageRepository;

import javax.servlet.http.HttpServletRequest;

import java.util.NoSuchElementException;

import static ru.job4j.Util.*;

@Service
public class MessageService {

    private final MessageRepository messages;
    private final RoomService rooms;
    private final PersonService persons;

    public MessageService(MessageRepository messages, RoomService rooms, PersonService persons) {
        this.messages = messages;
        this.rooms = rooms;
        this.persons = persons;
    }

    public void save(Message message, int id, HttpServletRequest request) {
        Room room = rooms.getById(id);
        String user = getUserFromToken(request);
        if (message.getContent().isEmpty()) {
            throw new EmptyArgumentException("Текст сообщения");
        }
        message.setAuthor(user);
        message.setRoom(room);
        messages.save(message);
    }

    public void update(Message message, int id, HttpServletRequest request) {
        Message persisted = findById(id);
        String user = getUserFromToken(request);
        boolean isAdmin = persons.getByName(user).getRole().getName().equals("ROLE_ADMIN");
        if (message.getContent().isEmpty()) {
            throw new EmptyArgumentException("Текст сообщения");
        }
        if (!(user.equals(persisted.getAuthor()) || isAdmin)) {
            throw new AuthorizationException("Редактирование сообщения");
        }
        persisted.setContent(message.getContent());
        messages.save(persisted);
    }

    public void delete(int id, HttpServletRequest request) {
        String user = getUserFromToken(request);
        boolean isAdmin = persons.getByName(user).getRole().getName().equals("ROLE_ADMIN");
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
