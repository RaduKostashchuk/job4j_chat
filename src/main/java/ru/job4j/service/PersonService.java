package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;
import ru.job4j.domain.Role;
import ru.job4j.repository.PersonRepository;
import ru.job4j.repository.RoleRepository;

@Service
public class PersonService {
    @Autowired
    private PersonRepository persons;

    @Autowired
    private RoleRepository roles;

    @Autowired
    private PasswordEncoder encoder;

    public Person save(Person person) {
        Person result = null;
        if (!persons.existsByName(person.getName())) {
            Role userRole = roles.findRoleByName("ROLE_USER");
            person.setRole(userRole);
            person.setPassword(encoder.encode(person.getPassword()));
            person.setEnabled(true);
            result = persons.save(person);
        }
        return result;
    }

    public Person getById(int id) {
        return persons.findById(id).orElse(null);
    }
}
