package com.javarush.questgame;

import java.util.ArrayList;
import java.util.List;


public class GameCreator {
    private final List<Location> gameMap = new ArrayList<>();
    private final List<Npc> nps = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private final List<Question> dialog = new ArrayList<>();

    private final List<Quest> quests = new ArrayList<>();

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

    public List<Npc> createNps(){
        Npc mrsStone = Npc.builder()
                .name("Mrs. Stone")
                .startMessageId(1)
                .build();
        nps.add(mrsStone);
        return nps;
    }

    public List<Question> createDialogWithMrsStone(){
        Question q1 = Question.builder()
                .id(1)
                .text("Good afternoon! Mr H. is away from home.With whom do I have the honor of speaking?")
                .answers(List.of(
                        Question.Answer.builder()
                                .text("Good afternoon! I am a Dr. Strange - colleague from work.")
                                .nextQuestionId(2)
                                .build(),
                        Question.Answer.builder()
                                .text("Good afternoon! I am a Dr. Watson - old friend of Mr. H.")
                                .nextQuestionId(3)
                                .build()
                ))
                .build();
        dialog.add(q1);

        Question q2 = Question.builder()
                .id(2)
                .text("Mr H. never talks about his colleagues. I even thought they didn't exist. Nice to meet you, how can I help?")
                .answers(List.of(
                        Question.Answer.builder()
                                .text("Mr H. is at a conference now and sent me to pick up some stuff from his lab.")
                                .nextQuestionId(4)
                                .build(),
                        Question.Answer.builder()
                                .text("I need to close the portal to the multiverse!")
                                .finishMessage("Looks like someone watched too much sci-fi.. Poor Mrs Stone got scared and kicked you out the door")
                                .build()
                ))
                .build();
        dialog.add(q2);

        Question q3 = Question.builder()
                .id(3)
                .text("Queerly... I know all the friends of Mr H. I will call him now.")
                .answers(List.of(
                        Question.Answer.builder()
                                .text("Oh don't worry, I'll come later when Mr H. is at home.")
                                .finishMessage("You failed the mission, we'll have to find a new agent.")
                                .build(),
                        Question.Answer.builder()
                                .text("Ok, i'll wait")
                                .finishMessage("OOh.. you better just leave.. Mr H. gave advice to call the police!")
                                .build()
                ))
                .build();
        dialog.add(q3);

        Question q4 = Question.builder()
                .id(4)
                .text("The door to the laboratory is always locked. Did Mr H. give you the key?")
                .answers(List.of(
                        Question.Answer.builder()
                                .text("Of course I have the key!")
                                .nextQuestionId(5)
                                .build(),
                        Question.Answer.builder()
                                .text("Unfortunately no.")
                                .nextQuestionId(6)
                                .build()
                ))
                .build();
        dialog.add(q4);

        Question q5 = Question.builder()
                .id(5)
                .text("Very good. House at your disposal!")
                .answers(List.of(
                        Question.Answer.builder()
                                .text("Thanks a lot!")
                                .build()
                ))
                .build();
        dialog.add(q5);

        Question q6 = Question.builder()
                .id(6)
                .text("Then you better go back and ask for the key. Good luck!")
                .answers(List.of(
                        Question.Answer.builder()
                                .text("Good bye!")
                                .finishMessage("Could be worse, let's try again!")
                                .build()
                ))
                .build();
        dialog.add(q6);



        return dialog;
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

    public List<Quest> createQuests(){
        Quest quest1 = Quest.builder()
                .id(1)
                .text("Talk to Mrs Stone")
                .build();
        quests.add(quest1);

        Quest quest2 = Quest.builder()
                .id(2)
                .text("Find the virus")
                .build();
        quests.add(quest2);

        Quest quest3 = Quest.builder()
                .id(3)
                .text("Find reagent")
                .build();
        quests.add(quest3);

        Quest quest4 = Quest.builder()
                .id(4)
                .text("Find formula")
                .build();
        quests.add(quest4);

        Quest quest5 = Quest.builder()
                .id(5)
                .text("Collect all the stars")
                .build();
        quests.add(quest5);

        Quest quest6 = Quest.builder()
                .id(6)
                .text("Find the key")
                .build();
        quests.add(quest6);

        return quests;
    }


}
