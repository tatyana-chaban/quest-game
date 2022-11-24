package com.javarush.tchaban.questgame.engine.entities;

import lombok.*;
@Data
@Builder
public class Housekeeper {
    private String name;
    private Integer startQuestionId;
}
