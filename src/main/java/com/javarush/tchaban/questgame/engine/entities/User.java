package com.javarush.tchaban.questgame.engine.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class User {
    private final String name;
    private Integer numberOfParties;
    private String currentLocationName;
    private String winMessage;
    private List<String> inventory = new ArrayList<>();
    private Map<Integer, Quest> quests = new HashMap<>();

    public User(String name) {
        this.name = name;
        numberOfParties = 0;
        currentLocationName = "hall";
        winMessage = "Congratulations, mission accomplished. The world is saved from a new pandemic!";
    }
    public void putInInventory(String item){
        inventory.add(item);
    }
    public boolean containsInInventory(String itemName){
        return inventory.contains(itemName);
    }

    public void takeQuest(Integer id, Quest quest){
        if(id == null){
            throw new  IllegalArgumentException("Id can't be null.");
        }

        if(quest == null){
            throw new  IllegalArgumentException("Quest can't be null.");
        }

        quests.put(id, quest);
    }

    public void incrementNumberOfParties(){
        numberOfParties++;
    }

    public void returnToStart(){
        inventory.clear();
        quests.clear();
        currentLocationName = "hall";
    }
}
