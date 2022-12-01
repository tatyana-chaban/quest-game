package com.javarush.tchaban.questgame.engine.services;

import com.javarush.tchaban.questgame.engine.entities.Question;
import com.javarush.tchaban.questgame.engine.repository.Repository;
import lombok.*;

@AllArgsConstructor
public class DialogService {
    private Repository<Integer, Question> questionRepository;

    public Question getQuestion(String currentQuestionIdName) {
        int currentQuestionId = getQuestionId(currentQuestionIdName);

        if (!questionRepository.contains(currentQuestionId)) {
            throw new IllegalArgumentException("Id " + currentQuestionId + " not found.");
        }

        return questionRepository.getByKey(currentQuestionId);
    }

    public int getQuestionId(String questionIdName) {
        if (questionIdName == null) {
            throw new IllegalArgumentException("QuestionIdName can't be null.");
        }

        int questionId;

        try {
            questionId = Integer.parseInt(questionIdName);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Id is not a number" + questionIdName, ex);
        }
        return questionId;
    }
}
