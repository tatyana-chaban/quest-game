package com.javarush.tchaban.questgame.engine.services;

import com.javarush.tchaban.questgame.engine.entities.Question;
import com.javarush.tchaban.questgame.engine.repository.Repository;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
public class DialogService {
    private static final Logger logger = LoggerFactory.getLogger(DialogService.class);
    private Repository<Integer, Question> questionRepository;

    public Question getQuestion(String currentQuestionIdName) {
        int currentQuestionId = getQuestionId(currentQuestionIdName);

        if (!questionRepository.contains(currentQuestionId)) {
            logger.error("Id " + currentQuestionId + " not found.");
            throw new IllegalArgumentException("Id " + currentQuestionId + " not found.");
        }

        return questionRepository.getByKey(currentQuestionId);
    }

    public int getQuestionId(String questionIdName) {
        if (questionIdName == null) {
            logger.error("QuestionIdName can't be null.");
            throw new IllegalArgumentException("QuestionIdName can't be null.");
        }

        int questionId;

        try {
            questionId = Integer.parseInt(questionIdName);
        } catch (NumberFormatException ex) {
            logger.error("Id is not a number" + questionIdName);
            throw new IllegalArgumentException("Id is not a number" + questionIdName, ex);
        }
        return questionId;
    }
}
