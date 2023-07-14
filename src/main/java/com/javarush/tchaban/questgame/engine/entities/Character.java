package com.javarush.tchaban.questgame.engine.entities;

import lombok.*;
@Data
@Builder
public class Character {
    private String name;
    private Integer startQuestionId;
}
