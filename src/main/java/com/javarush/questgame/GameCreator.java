package com.javarush.questgame;

import java.util.ArrayList;
import java.util.List;


public class GameCreator {
    private final List<Location> gameMap = new ArrayList<>();
    private final List<Nps> nps = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();

    public List<Location> createMap() {
        Location hall = Location.builder()
                .name("hall")
                .availableLocations(List.of("kitchen", "stairs", "bathroom"))
                .nps(List.of("Mrs. Stone"))
                .items(List.of("star1"))
                .build();
        gameMap.add(hall);

        Location kitchen = Location.builder()
                .name("kitchen")
                .availableLocations(List.of("hall"))
                .items(List.of("bottle1", "secretFormula", "star2"))
                .build();
        gameMap.add(kitchen);

        Location bathroom = Location.builder()
                .name("bathroom")
                .availableLocations(List.of("hall"))
                .items(List.of("star3"))
                .build();
        gameMap.add(bathroom);

        Location stairs = Location.builder()
                .name("stairs")
                .availableLocations(List.of("hall", "laboratory", "bedroom"))
                .items(List.of("star4"))
                .build();
        gameMap.add(stairs);

        Location laboratory = Location.builder()
                .name("laboratory")
                .availableLocations(List.of("stairs"))
                .items(List.of("bottle2", "star5"))
                .build();
        gameMap.add(laboratory);

        Location bedroom = Location.builder()
                .name("bedroom")
                .availableLocations(List.of("stairs"))
                .items(List.of("key", "star6"))
                .build();
        gameMap.add(bedroom);

        return gameMap;
    }

    public List<Nps> createNps(){
        Nps mrsStone = Nps.builder()
                .name("Mrs. Stone")
                .build();
        nps.add(mrsStone);
        return nps;
    }

    public List<Item> createItems(){
        Item bottle1 = Item.builder()
                .name("bottle1")
                .build();
        items.add(bottle1);

        Item bottle2 = Item.builder()
                .name("bottle2")
                .build();
        items.add(bottle2);

        Item secretFormula = Item.builder()
                .name("secretFormula")
                .build();
        items.add(secretFormula);

        Item star1 = Item.builder()
                .name("star1")
                .build();
        items.add(star1);

        Item star2 = Item.builder()
                .name("star2")
                .build();
        items.add(star2);

        Item star3 = Item.builder()
                .name("star3")
                .build();
        items.add(star3);

        Item star4 = Item.builder()
                .name("star4")
                .build();
        items.add(star4);

        Item star5 = Item.builder()
                .name("star5")
                .build();
        items.add(star5);

        Item star6 = Item.builder()
                .name("star6")
                .build();
        items.add(star6);

        Item key = Item.builder()
                .name("key")
                .build();
        items.add(key);

        return items;
    }


}
