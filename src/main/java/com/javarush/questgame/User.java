package com.javarush.questgame;


import lombok.*;

@Getter
@Setter
public class User {
    private final String name;
    private int numberOfParties;

    private Location currentLocation;

    public User(String name){
        this.name = name;
        numberOfParties = 0;
    }
}
