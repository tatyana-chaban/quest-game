package com.javarush.questgame;

import lombok.*;

@Data
@Builder
public class Quest {
    private Integer id;
    private String text;

    private boolean isFinished;

}
