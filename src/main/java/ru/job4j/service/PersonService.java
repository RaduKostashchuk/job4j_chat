package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;
import ru.job4j.domain.Role;
import ru.job4j.repository.PersonRepository;
import ru.job4j.repository.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
            result = persons.save(person);
        }
        return result;
    }

    public List<Person> getAll() {
        return StreamSupport.stream(persons.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Person getById(int id) {
        return persons.findById(id).orElse(null);
    }

    public void delete(int id) {
        Person person = new Person();
        person.setId(id);
        persons.delete(person);
    }

}
