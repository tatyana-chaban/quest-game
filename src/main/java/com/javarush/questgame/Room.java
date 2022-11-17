package com.javarush.questgame;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Room {
    private String name;
    private List<Door> doors = new ArrayList<>();

    public Room(String name){
        this.name = name;
    }

    public void addDoor(Door door){
        doors.add(door);
    }




}
