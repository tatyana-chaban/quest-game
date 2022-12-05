package com.javarush.tchaban.questgame.engine.predicates;

import com.javarush.tchaban.questgame.engine.entities.User;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@AllArgsConstructor
public class WinCheckPredicate implements Predicate<User> {
    private Set<String> itemsNames;
    @Override
    public boolean test(User user) {
        return new HashSet<>(user.getInventory()).containsAll(itemsNames);
    }
}
