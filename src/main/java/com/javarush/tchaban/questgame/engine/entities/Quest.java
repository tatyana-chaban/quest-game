package com.javarush.tchaban.questgame.engine.entities;

import lombok.*;

import java.util.function.Predicate;

@Data
@Builder
public class Quest {
    private Integer id;
    private String text;
    private Predicate<User> isFinished;
    public boolean isFinished(User user) {
        return isFinished.test(user);
    }
}
