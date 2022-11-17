package com.javarush.questgame;

import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private final Map<String, Room> gameMap = new HashMap<>();

    public void addRoom(String name, Room room){
        gameMap.put(name,room);
    }
}
