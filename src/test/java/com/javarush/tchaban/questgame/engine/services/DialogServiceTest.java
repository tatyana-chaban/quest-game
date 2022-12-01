package com.javarush.tchaban.questgame.engine.services;

import com.javarush.tchaban.questgame.engine.entities.Question;
import com.javarush.tchaban.questgame.engine.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DialogServiceTest {
    @Mock
    Repository<Integer, Question> questionRepository;
    private DialogService dialogService;

    @BeforeEach
    void setUp() {
        dialogService = new DialogService(questionRepository);
    }

    @Test
    void test_getQuestion_ThrowException_WhenQuestionIdNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> dialogService.getQuestion(null));
    }

    @Test
    void test_getQuestion_ThrowException_WhenQuestionIdNameIsNotInteger() {
        assertThrows(IllegalArgumentException.class,
                () -> dialogService.getQuestion("test"));
    }

    @Test
    void test_getQuestion_ThrowException_WhenQuestionNotFound() {
        when(questionRepository.contains(5)).thenReturn(false);

        assertThrows(IllegalArgumentException.class,
                () -> dialogService.getQuestion("5"));
    }

    @Test
    void test_getQuestion_WhenQuestionIdIsCorrect() {
        Question question1 = mock(Question.class);

        when(questionRepository.contains(1)).thenReturn(true);
        when(questionRepository.getByKey(1)).thenReturn(question1);

        assertEquals(question1, dialogService.getQuestion("1"));
    }

    @Test
    void test_getQuestionId_ThrowException_WhenQuestionIdNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> dialogService.getQuestionId(null));
    }

    @Test
    void test_getQuestionId_ThrowException_WhenQuestionIdNameIsNotInteger() {
        assertThrows(IllegalArgumentException.class,
                () -> dialogService.getQuestionId("test"));
    }

    @Test
    void test_getQuestionId_WhenQuestionIdIsCorrect() {
        int expected = 1;

        assertEquals(expected, dialogService.getQuestionId("1"));
    }
}
