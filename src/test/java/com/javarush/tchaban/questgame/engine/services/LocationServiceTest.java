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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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
    void setUp() {
        locationService = new LocationService(locationRepository, itemRepository, winCheck);
    }

    @Test
    void test_isWin_ThrowException_WhenUserIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> locationService.isWin(null));
    }

    @Test
    void test_isWin_WhenTrue() {
        User mockedUser = mock(User.class);
        when(winCheck.test(mockedUser)).thenReturn(true);

        assertTrue(locationService.isWin(mockedUser));
    }

    @Test
    void test_isWin_WhenFalse() {
        User mockedUser = mock(User.class);
        when(winCheck.test(mockedUser)).thenReturn(false);

        assertFalse(locationService.isWin(mockedUser));
    }

    @Test
    void test_getLocation_ThrowException_WhenUserIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> locationService.getLocation(null));
    }

    @Test
    void test_getLocation_ThrowException_WhenLocationDoesNotExist() {
        when(locationRepository.contains(eq("test"))).thenReturn(false);

        assertThrows(IllegalArgumentException.class,
                () -> locationService.getLocation("test"));
    }

    @Test
    void test_getLocation_WhenLocationExist() {
        Location mockedLocation = mock(Location.class);

        when(locationRepository.contains(eq("test"))).thenReturn(true);
        when(locationRepository.getByKey(eq("test"))).thenReturn(mockedLocation);

        assertEquals(mockedLocation, locationService.getLocation("test"));
    }

    @Test
    void test_getAvailableLocations_ThrowException_WhenLocationIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> locationService.getAvailableLocations(null));
    }

    @Test
    void test_getAvailableLocations() {
        Location currentLocation = Location.builder()
                .name("current")
                .availableLocations(List.of("available"))
                .build();
        Location availableLocation = Location.builder()
                .name("available")
                .build();

        when(locationRepository.contains(eq("available"))).thenReturn(true);
        when(locationRepository.getByKey(eq("available"))).thenReturn(availableLocation);

        List<Location> expected = List.of(availableLocation);

        assertArrayEquals(expected.toArray(), locationService.getAvailableLocations(currentLocation).toArray());
    }

    @Test
    void test_getAvailableItems_ThrowException_WhenUserIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> locationService.getAvailableItems(null, mock(Location.class)));
    }

    @Test
    void test_getAvailableItems_ThrowException_WhenLocationIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> locationService.getAvailableItems(mock(User.class), null));
    }

    @Test
    void test_getAvailableItems_ThrowException_WhenBothArgumentAreNull() {
        assertThrows(IllegalArgumentException.class,
                () -> locationService.getAvailableItems(null, null));
    }

    @Test
    void test_getAvailableItems_Correct() {
        User user = mock(User.class);
        Location location = mock(Location.class);
        Item item = Item.builder()
                .name("testItem")
                .build();

        List<Item> expected = List.of(item);

        when(location.getItems()).thenReturn(List.of("testItem"));
        when(user.containsInInventory(eq("testItem"))).thenReturn(false);
        when(itemRepository.contains(eq("testItem"))).thenReturn(true);
        when(itemRepository.getByKey(eq("testItem"))).thenReturn(item);

        assertArrayEquals(expected.toArray(), locationService.getAvailableItems(user, location).toArray());
    }

    @Test
    void test_getAvailableItems_ThrowException_WhenItemNotExist() {
        User user = mock(User.class);
        Location location = mock(Location.class);

        when(location.getItems()).thenReturn(List.of("testItem"));
        when(itemRepository.contains(eq("testItem"))).thenReturn(false);

        assertThrows(IllegalArgumentException.class,
                () -> locationService.getAvailableItems(user, location));
    }

    @Test
    void test_moveToLocation_ThrowException_WhenUserIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> locationService.moveToLocation(null, "test"));
    }

    @Test
    void test_moveToLocation_ThrowException_WhenLocationNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> locationService.moveToLocation(mock(User.class), null));
    }

    @Test
    void test_moveToLocation_ThrowException_WhenBothArgumentAreNull() {
        assertThrows(IllegalArgumentException.class,
                () -> locationService.moveToLocation(null, null));
    }

    @Test
    void test_moveToLocation_IsCorrect() {
        User user = new User("user");
        user.setCurrentLocationName("currentLocation");

        Location currentLocation = Location.builder().name("currentLocation").availableLocations(List.of("nextLocation")).build();
        Location nextLocation = Location.builder().name("nextLocation").build();

        when(locationRepository.contains(eq("currentLocation"))).thenReturn(true);
        when(locationRepository.getByKey(eq("currentLocation"))).thenReturn(currentLocation);
        when(locationRepository.contains(eq("nextLocation"))).thenReturn(true);
        when(locationRepository.getByKey(eq("nextLocation"))).thenReturn(nextLocation);

        locationService.moveToLocation(user, nextLocation.getName());

        assertEquals(user.getCurrentLocationName(), nextLocation.getName());
    }

    @Test
    void test_moveToLocation_ThrowException_WhenNextLocationIsUnavailable() {
        User user = new User("user");
        user.setCurrentLocationName("currentLocation");

        Location currentLocation = Location.builder().name("currentLocation").build();
        Location nextLocation = Location.builder().name("nextLocation").build();

        when(locationRepository.contains(eq("currentLocation"))).thenReturn(true);
        when(locationRepository.getByKey(eq("currentLocation"))).thenReturn(currentLocation);
        when(locationRepository.contains(eq("nextLocation"))).thenReturn(true);
        when(locationRepository.getByKey(eq("nextLocation"))).thenReturn(nextLocation);

        assertThrows(IllegalArgumentException.class,
                () -> locationService.moveToLocation(user, nextLocation.getName()));
    }

    @Test
    void test_takeItem_ThrowException_WhenUserIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> locationService.takeItem(null, "test"));
    }

    @Test
    void test_takeItem_ThrowException_WhenLocationIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> locationService.takeItem(mock(User.class), null));
    }

    @Test
    void test_takeItem_ThrowException_WhenBothArgumentAreNull() {
        assertThrows(IllegalArgumentException.class,
                () -> locationService.takeItem(null, null));
    }

    @Test
    void test_takeItem_WhenItemExist() {
        User user = mock(User.class);

        when(itemRepository.contains(eq("test"))).thenReturn(true);

        locationService.takeItem(user, "test");
        verify(user, times(1)).putInInventory("test");
    }
}
