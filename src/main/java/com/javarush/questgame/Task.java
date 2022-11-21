package com.javarush.questgame;

import lombok.*;

@Data
@Builder
public class Task {
    private Integer id;
    private String text;

    private boolean isFinished;

}
