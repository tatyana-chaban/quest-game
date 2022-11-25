package com.javarush.tchaban.questgame.engine.predicates;

import com.javarush.tchaban.questgame.engine.entities.User;
import lombok.AllArgsConstructor;

import java.util.function.Predicate;

@AllArgsConstructor
public class QuestCheckPredicate implements Predicate<User> {
    private String itemName;
    @Override
    public boolean test(User user) {
        return user.containsInInventory(itemName);
    }
}
