package com.javarush.tchaban.questgame.engine.services;

import com.javarush.tchaban.questgame.engine.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FinishServiceTest {
    FinishService finishService;

    @BeforeEach
    void setUp() {
        finishService = new FinishService();
    }

    @Test
    void test_finishGameForThisUser_ThrowException_WhenUserIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> finishService.finishGameForThisUser(null));
    }

    @Test
    void test_finishGameForThisUser() {
        User expectedUser = new User("test");
        expectedUser.incrementNumberOfParties();

        User userForTest = new User("test");
        userForTest.putInInventory("testItem");
        userForTest.setCurrentLocationName("testLocation");

        finishService.finishGameForThisUser(userForTest);
        assertEquals(expectedUser, userForTest);
    }

}