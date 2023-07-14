package com.javarush.tchaban.questgame.engine.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Data
@Builder
@AllArgsConstructor
public class Location {
    private final String name;
    private Predicate<User> isOpen;

    @Builder.Default
    private final List<String> availableLocations = new ArrayList<>();
    @Builder.Default
    private final List<String> items = new ArrayList<>();

    public boolean isOpen(User user) {
        if (isOpen == null) {
            return true;
        } else {
            return isOpen.test(user);
        }
    }
}
