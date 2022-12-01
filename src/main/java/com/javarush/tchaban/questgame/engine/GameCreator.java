package com.javarush.tchaban.questgame.engine;

import com.javarush.tchaban.questgame.engine.entities.*;
import com.javarush.tchaban.questgame.engine.entities.Character;
import com.javarush.tchaban.questgame.engine.predicates.ItemCheckPredicate;
import java.util.ArrayList;
import java.util.List;

public class GameCreator {
    private final List<Location> gameMap = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private final List<Question> dialog = new ArrayList<>();
    private final List<Quest> quests = new ArrayList<>();

    public List<Location> createMap() {
        Location hall = Location.builder()
                .name("hall")
                .availableLocations(List.of("kitchen", "stairs", "bathroom"))
                .build();
        gameMap.add(hall);

        Location kitchen = Location.builder()
                .name("kitchen")
                .availableLocations(List.of("hall"))
                .items(List.of("bottle with reagent", "secret formula"))
                .build();
        gameMap.add(kitchen);

        Location bathroom = Location.builder()
                .name("bathroom")
                .availableLocations(List.of("hall"))
                .items(List.of("star"))
                .build();
        gameMap.add(bathroom);

        Location stairs = Location.builder()
                .name("stairs")
                .availableLocations(List.of("hall", "laboratory", "bedroom"))
                .build();
        gameMap.add(stairs);

        Location laboratory = Location.builder()
                .name("laboratory")
                .isOpen(new ItemCheckPredicate("key"))
                .availableLocations(List.of("stairs"))
                .items(List.of("test tube with virus"))
                .build();
        gameMap.add(laboratory);

        Location bedroom = Location.builder()
                .name("bedroom")
                .availableLocations(List.of("stairs"))
                .items(List.of("key"))
                .build();
        gameMap.add(bedroom);

        return gameMap;
    }

    public Character createCharacter() {
        return Character.builder()
                .name("Mrs. Stone")
                .startQuestionId(1)
                .build();
    }

    public List<Question> createDialogWithMrsStone() {
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
                                .finishMessage("Oh.. you better just leave.. Mr H. gave advice to call the police!")
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

    public List<Item> createItems() {
        items.add(Item.builder()
                .name("bottle with reagent")
                .build());

        items.add(Item.builder()
                .name("test tube with virus")
                .build());

        items.add(Item.builder()
                .name("secret formula")
                .build());

        items.add(Item.builder()
                .name("star")
                .build());

        items.add(Item.builder()
                .name("key")
                .build());

        return items;
    }

    public List<Quest> createQuests() {
        quests.add(Quest.builder()
                .id(1)
                .text("Find the key")
                .isFinished(new ItemCheckPredicate("key"))
                .build());

        quests.add(Quest.builder()
                .id(2)
                .text("Find reagent")
                .isFinished(new ItemCheckPredicate("bottle with reagent"))
                .build());

        quests.add(Quest.builder()
                .id(3)
                .text("Find formula")
                .isFinished(new ItemCheckPredicate("secret formula"))
                .build());

        quests.add(Quest.builder()
                .id(4)
                .text("Find bonus star")
                .isFinished(new ItemCheckPredicate("star"))
                .build());

        quests.add(Quest.builder()
                .id(5)
                .text("Find the virus")
                .isFinished(new ItemCheckPredicate("test tube with virus"))
                .build());

        return quests;
    }


}
