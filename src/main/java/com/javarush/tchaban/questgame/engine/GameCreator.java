package com.javarush.tchaban.questgame.engine;

import com.javarush.tchaban.questgame.engine.entities.*;
import com.javarush.tchaban.questgame.engine.entities.Character;
import com.javarush.tchaban.questgame.engine.predicates.ItemCheckPredicate;

import java.util.ArrayList;
import java.util.List;

import static com.javarush.tchaban.questgame.engine.enums.Items.*;
import static com.javarush.tchaban.questgame.engine.enums.Locations.*;

public class GameCreator {

    public List<Location> createMap() {
        List<Location> gameMap = new ArrayList<>();
        Location hall = Location.builder()
                .name(HALL.getName())
                .availableLocations(List.of(KITCHEN.getName(), STAIRS.getName(), BATHROOM.getName()))
                .build();
        gameMap.add(hall);

        Location kitchen = Location.builder()
                .name(KITCHEN.getName())
                .availableLocations(List.of(HALL.getName()))
                .items(List.of(REAGENT.getName(), FORMULA.getName()))
                .build();
        gameMap.add(kitchen);

        Location bathroom = Location.builder()
                .name(BATHROOM.getName())
                .availableLocations(List.of(HALL.getName()))
                .items(List.of(STAR.getName()))
                .build();
        gameMap.add(bathroom);

        Location stairs = Location.builder()
                .name(STAIRS.getName())
                .availableLocations(List.of(HALL.getName(), LABORATORY.getName(), BEDROOM.getName()))
                .build();
        gameMap.add(stairs);

        Location laboratory = Location.builder()
                .name(LABORATORY.getName())
                .isOpen(new ItemCheckPredicate(KEY.getName()))
                .availableLocations(List.of(STAIRS.getName()))
                .items(List.of(VIRUS.getName()))
                .build();
        gameMap.add(laboratory);

        Location bedroom = Location.builder()
                .name(BEDROOM.getName())
                .availableLocations(List.of(STAIRS.getName()))
                .items(List.of(KEY.getName()))
                .build();
        gameMap.add(bedroom);

        return List.copyOf(gameMap);
    }

    public Character createCharacter() {
        return Character.builder()
                .name("Mrs. Stone")
                .startQuestionId(1)
                .build();
    }

    public List<Question> createDialogWithMrsStone() {
        List<Question> dialog = new ArrayList<>();
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
                                .text("Ok, I'll wait")
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

        return List.copyOf(dialog);
    }

    public List<Item> createItems() {
        List<Item> items = new ArrayList<>();
        items.add(Item.builder()
                .name(REAGENT.getName())
                .build());

        items.add(Item.builder()
                .name(VIRUS.getName())
                .build());

        items.add(Item.builder()
                .name(FORMULA.getName())
                .build());

        items.add(Item.builder()
                .name(STAR.getName())
                .build());

        items.add(Item.builder()
                .name(KEY.getName())
                .build());

        return List.copyOf(items);
    }

    public List<Quest> createQuests() {
        List<Quest> quests = new ArrayList<>();
        quests.add(Quest.builder()
                .id(1)
                .text("Find the key")
                .isFinished(new ItemCheckPredicate(KEY.getName()))
                .build());

        quests.add(Quest.builder()
                .id(2)
                .text("Find reagent")
                .isFinished(new ItemCheckPredicate(REAGENT.getName()))
                .build());

        quests.add(Quest.builder()
                .id(3)
                .text("Find formula")
                .isFinished(new ItemCheckPredicate(FORMULA.getName()))
                .build());

        quests.add(Quest.builder()
                .id(4)
                .text("Find bonus star")
                .isFinished(new ItemCheckPredicate(STAR.getName()))
                .build());

        quests.add(Quest.builder()
                .id(5)
                .text("Find the virus")
                .isFinished(new ItemCheckPredicate(VIRUS.getName()))
                .build());

        return List.copyOf(quests);
    }
}
