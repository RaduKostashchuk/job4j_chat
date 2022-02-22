package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.Person;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    boolean existsByName(String name);

    Optional<Person> findByName(String name);
}
