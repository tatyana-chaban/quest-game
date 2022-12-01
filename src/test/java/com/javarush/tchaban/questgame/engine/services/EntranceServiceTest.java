package com.javarush.tchaban.questgame.engine.services;

import com.javarush.tchaban.questgame.engine.entities.Character;
import com.javarush.tchaban.questgame.engine.entities.Quest;
import com.javarush.tchaban.questgame.engine.entities.User;
import com.javarush.tchaban.questgame.engine.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EntranceServiceTest {
    @Mock
    private Repository<String, User> userRepository;
    @Mock
    private Repository<Integer, Quest> questRepository;
    @Mock
    private Character mrsStone;

    EntranceService entranceService;

    @BeforeEach
    void setUp() {
        entranceService = new EntranceService(userRepository, questRepository, mrsStone);
    }

    @Test
    void test_getOrCreateUser_ThrowException_WhenUserNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> entranceService.getOrCreateUser(null));
    }

    @Test
    void test_getOrCreateUser_ThrowException_WhenUserNameIsBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> entranceService.getOrCreateUser("   "));
    }

    @Test
    void test_getOrCreateUser_WhenUserExist() {
        User mockedUser = mock(User.class);


        when(userRepository.contains(eq("test"))).thenReturn(true);
        when(userRepository.getByKey(eq("test"))).thenReturn(mockedUser);

        assertEquals(mockedUser, entranceService.getOrCreateUser("test"));
    }

    @Test
    void test_getOrCreateUser_CreateNewUser() {
        User newUser = new User("test");
        Quest quest1 = mock(Quest.class);
        newUser.takeQuest(1,quest1);

        when(userRepository.contains(eq("test"))).thenReturn(false);
        when(questRepository.getAllKeys()).thenReturn(Set.of(1));
        when(questRepository.getByKey(eq(1))).thenReturn(quest1);

        assertEquals(newUser, entranceService.getOrCreateUser("test"));
    }

    @Test
    void test_getCharacter(){
        Character expected = mrsStone;
        assertEquals(expected, entranceService.getCharacter());
    }

}
