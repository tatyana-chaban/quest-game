package com.javarush.questgame;


import lombok.Getter;
import lombok.Setter;

@Getter
public class User {
    private final String name;
    @Setter
    private int numberOfParties;

    public User(String name){
        this.name = name;
        numberOfParties = 0;
    }
}
