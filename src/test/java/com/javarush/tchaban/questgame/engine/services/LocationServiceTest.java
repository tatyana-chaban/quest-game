package com.javarush.tchaban.questgame.engine.services;

import com.javarush.tchaban.questgame.engine.entities.Item;
import com.javarush.tchaban.questgame.engine.entities.Location;
import com.javarush.tchaban.questgame.engine.entities.User;
import com.javarush.tchaban.questgame.engine.predicates.WinCheckPredicate;
import com.javarush.tchaban.questgame.engine.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {
    @Mock
    private Repository<String, Location> locationRepository;
    @Mock
    private Repository<String, Item> itemRepository;
    @Mock
    private WinCheckPredicate winCheck;

    LocationService locationService;

    @BeforeEach
    void setUp(){
        locationService = new LocationService(locationRepository, itemRepository, winCheck);
    }
    @Test
    void test_isWin_ThrowException_WhenUserIsNull(){
        assertThrows(IllegalArgumentException.class,
                () -> locationService.isWin(null));
    }

    @Test
    void test_isWin_WhenTrue(){
        User mockedUser = mock(User.class);
        when(winCheck.test(mockedUser)).thenReturn(true);

        assertTrue(locationService.isWin(mockedUser));
    }

    @Test
    void test_isWin_WhenFalse(){
        User mockedUser = mock(User.class);
        when(winCheck.test(mockedUser)).thenReturn(false);

        assertFalse(locationService.isWin(mockedUser));
    }

    @Test
    void test_getLocation_ThrowException_WhenUserIsNull(){
        assertThrows(IllegalArgumentException.class,
                () -> locationService.getLocation(null));
    }

    @Test
    void test_isWin_ThrowException_WhenLocationDoesNotExist(){
        when(locationRepository.contains(eq("test"))).thenReturn(false);

        assertThrows(IllegalArgumentException.class,
                () -> locationService.getLocation("test"));
    }

    @Test
    void test_isWin_WhenLocationExist(){
        Location mockedLocation = mock(Location.class);

        when(locationRepository.contains(eq("test"))).thenReturn(true);
        when(locationRepository.getByKey(eq("test"))).thenReturn(mockedLocation);

        assertEquals(mockedLocation, locationService.getLocation("test"));
    }


}
