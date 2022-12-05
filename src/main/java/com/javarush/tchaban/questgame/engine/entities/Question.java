package com.javarush.tchaban.questgame.engine.entities;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Question {
    private Integer id;
    private String text;
    private List<Answer> answers;

    @Data
    @AllArgsConstructor
    @Builder
    public static class Answer {
        private String text;
        private String finishMessage;
        private Integer nextQuestionId;
    }
}
