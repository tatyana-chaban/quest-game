package com.javarush.tchaban.questgame.engine.predicates;

import com.javarush.tchaban.questgame.engine.entities.Item;
import com.javarush.tchaban.questgame.engine.entities.User;
import com.javarush.tchaban.questgame.engine.repository.Repository;
import lombok.*;

import java.util.HashSet;
import java.util.function.Predicate;

@AllArgsConstructor
public class WinCheckPredicate implements Predicate<User> {
    private Repository<String, Item> itemsRepository;
    @Override
    public boolean test(User user) {
        return new HashSet<>(user.getInventory()).containsAll(itemsRepository.getAllKeys());
    }
}
