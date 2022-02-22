package ru.job4j.dto;

import ru.job4j.domain.Room;

public class RoomDTO {
    private int id;
    private String name;

    public static RoomDTO of(Room room) {
        RoomDTO model = new RoomDTO();
        model.id = room.getId();
        model.name = room.getName();
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
 }
