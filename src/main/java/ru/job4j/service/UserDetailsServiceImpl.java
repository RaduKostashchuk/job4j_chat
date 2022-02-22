package ru.job4j.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonService persons;

    public UserDetailsServiceImpl(PersonService persons) {
        this.persons = persons;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = persons.getByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getName(), user.getPassword(), Collections.emptyList());
    }
}
