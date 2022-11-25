package com.javarush.tchaban.questgame.engine.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class User {
    private final String name;
    private AtomicInteger numberOfParties;
    private String currentLocationName;
    private String winMessage;
    private List<String> inventory = new ArrayList<>();
    private List<Integer> quests = new ArrayList<>();

    public User(String name) {
        this.name = name;
        numberOfParties = new AtomicInteger(0);
        currentLocationName = "hall";
        winMessage = "Congratulations, mission accomplished. The world is saved from a new pandemic!";
    }
    public void putInInventory(String item){
        inventory.add(item);
    }
    public boolean containsInInventory(String itemName){
        return inventory.contains(itemName);
    }

    public void incrementNumberOfParties(){
        numberOfParties.incrementAndGet();
    }
    public int getNumberOfParties(){
        return numberOfParties.get();
    }

    public void returnToStart(){
        inventory.clear();
        quests.clear();
        currentLocationName = "hall";
    }
}
