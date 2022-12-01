package com.javarush.tchaban.questgame.engine.services;

import com.javarush.tchaban.questgame.engine.entities.Item;
import com.javarush.tchaban.questgame.engine.entities.Location;
import com.javarush.tchaban.questgame.engine.entities.User;
import com.javarush.tchaban.questgame.engine.predicates.WinCheckPredicate;
import com.javarush.tchaban.questgame.engine.repository.Repository;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class LocationService {
    private Repository<String, Location> locationRepository;
    private Repository<String, Item> itemRepository;
    private WinCheckPredicate winCheck;

    public boolean isWin(User user) {
        validateUser(user);
        return winCheck.test(user);
    }

    public Location getLocation(String locationName) {
        validateLocationName(locationName);

        if (!locationRepository.contains(locationName)) {
            throw new IllegalArgumentException("Location " + locationName + " not found.");
        }

        return locationRepository.getByKey(locationName);
    }

    public List<Location> getAvailableLocations(Location currentLocation) {
        validateLocation(currentLocation);

        List<Location> availableLocations = new ArrayList<>();
        for (String locationName : currentLocation.getAvailableLocations()) {
            var location = locationRepository.getByKey(locationName);
            availableLocations.add(location);
        }
        return availableLocations;
    }

    public List<Item> getAvailableItems(User user, Location currentLocation) {
        validateUser(user);
        validateLocation(currentLocation);

        List<Item> availableItems = new ArrayList<>();
        for (String itemName : currentLocation.getItems()) {
            if (user.getInventory().contains(itemName)) {
                continue;
            }
            var item = itemRepository.getByKey(itemName);
            availableItems.add(item);
        }
        return availableItems;
    }

    public void moveToLocation(User user, String nextLocationName) {
        validateUser(user);
        validateLocationName(nextLocationName);

        validateMoveToLocation(user, nextLocationName);

        user.setCurrentLocationName(nextLocationName);
    }

    public void takeItem(User user, String itemName) {
        validateUser(user);
        validateItemName(itemName);

        if (!itemRepository.contains(itemName)) {
            throw new IllegalArgumentException("Item " + itemName + "is not found.");
        }

        user.putInInventory(itemName);
    }

    private void validateMoveToLocation(User user, String nextLocationName) {
        validateUser(user);
        validateLocationName(nextLocationName);

        var availableLocations = getAvailableLocations(getLocation(user.getCurrentLocationName()));
        Location nextLocation = getLocation(nextLocationName);
        if (!availableLocations.contains(nextLocation)) {
            throw new IllegalArgumentException("Location " + nextLocationName + " unavailable.");
        }
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can't be null.");
        }
    }

    private void validateLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location can't be null.");
        }
    }

    private void validateLocationName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("LocationName can't be null.");
        }
    }

    private void validateItemName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("ItemName can't be null.");
        }
    }
}
