package com.javarush.questgame;

import lombok.*;

@Data
@Builder
public class Npc {
    private String name;
    private Integer startMessageId;
}
