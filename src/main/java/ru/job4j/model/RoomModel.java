package ru.job4j.model;

import ru.job4j.domain.Room;

public class RoomModel {
    private int id;
    private String name;
    private String owner;

    public static RoomModel of(Room room) {
        RoomModel model = new RoomModel();
        model.id = room.getId();
        model.name = room.getName();
        model.owner = room.getOwner();
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
