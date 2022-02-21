package ru.job4j.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;
import ru.job4j.domain.Role;
import ru.job4j.exception.AlreadyExistException;
import ru.job4j.exception.EmptyArgumentException;
import ru.job4j.exception.RegistrationException;
import ru.job4j.repository.PersonRepository;
import ru.job4j.repository.RoleRepository;

import java.util.NoSuchElementException;

@Service
public class PersonService {
    private final PersonRepository persons;
    private final RoleRepository roles;
    private final  PasswordEncoder encoder;

    public PersonService(PersonRepository persons, RoleRepository roles, PasswordEncoder encoder) {
        this.persons = persons;
        this.roles = roles;
        this.encoder = encoder;
            }

    public Person save(Person person) {
        String password = person.getPassword();
        String confirm = person.getConfirm();
        String name = person.getName();
        if (name.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            throw new EmptyArgumentException("логин и/или пароль");
        }
        if (!password.equals(confirm)) {
            throw new RegistrationException("Пароль не подтвержден");
        }
        if (persons.existsByName(person.getName())) {
            throw new AlreadyExistException("Пользователь");
        }
        Role userRole = roles.findRoleByName("ROLE_USER");
        person.setRole(userRole);
        person.setPassword(encoder.encode(person.getPassword()));
        person.setEnabled(true);
        return persons.save(person);
    }

    public Person getById(int id) {
        return persons.findById(id).orElseThrow(() -> new NoSuchElementException("Пользователь не найден"));
    }
}
