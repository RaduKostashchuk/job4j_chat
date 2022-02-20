package ru.job4j.model;

import ru.job4j.domain.Person;
import ru.job4j.domain.Role;

import java.util.Objects;

public class PersonModel {
    private int id;
    private String name;
    private Role role;

    public static PersonModel of(Person person) {
        PersonModel model = new PersonModel();
        model.id = person.getId();
        model.name = person.getName();
        model.role = person.getRole();
        return model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonModel that = (PersonModel) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PersonModel{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", role=" + role
                + '}';
    }
}
