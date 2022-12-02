package com.javarush.tchaban.questgame.engine.services;

import com.javarush.tchaban.questgame.engine.entities.Item;
import com.javarush.tchaban.questgame.engine.entities.Location;
import com.javarush.tchaban.questgame.engine.entities.User;
import com.javarush.tchaban.questgame.engine.predicates.WinCheckPredicate;
import com.javarush.tchaban.questgame.engine.repository.Repository;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class LocationService {

    private static final Logger logger = LoggerFactory.getLogger(LocationService.class);
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
            logger.error("Location " + locationName + " not found.");
            throw new IllegalArgumentException("Location " + locationName + " not found.");
        }

        return locationRepository.getByKey(locationName);
    }

    public List<Location> getAvailableLocations(Location currentLocation) {
        validateLocation(currentLocation);

        List<Location> availableLocations = new ArrayList<>();
        currentLocation
                .getAvailableLocations()
                .forEach(locationName -> availableLocations.add(getLocation(locationName)));

        return availableLocations;
    }

    public List<Item> getAvailableItems(User user, Location currentLocation) {
        validateUser(user);
        validateLocation(currentLocation);

        List<Item> availableItems = new ArrayList<>();
        for (String itemName : currentLocation.getItems()) {
            if (user.containsInInventory(itemName)) {
                continue;
            }
            if (!itemRepository.contains(itemName)) {
                logger.error("Item " + itemName + " not found.");
                throw new IllegalArgumentException("Item " + itemName + " not found.");
            }

            availableItems.add(itemRepository.getByKey(itemName));
        }
        return availableItems;
    }

    public void moveToLocation(User user, String nextLocationName) {
        validateUser(user);
        validateLocationName(nextLocationName);

        Location currentLocation = getLocation(user.getCurrentLocationName());
        List<Location> availableLocations = getAvailableLocations(currentLocation);
        Location nextLocation = getLocation(nextLocationName);

        if (!availableLocations.contains(nextLocation)) {
            logger.error("Location " + nextLocationName + " unavailable.");
            throw new IllegalArgumentException("Location " + nextLocationName + " unavailable.");
        }

        user.setCurrentLocationName(nextLocationName);
    }

    public void takeItem(User user, String itemName) {
        validateUser(user);
        validateItemName(itemName);

        if (!itemRepository.contains(itemName)) {
            logger.error("Item " + itemName + "is not found.");
            throw new IllegalArgumentException("Item " + itemName + "is not found.");
        }

        user.putInInventory(itemName);
    }

    private void validateUser(User user) {
        if (user == null) {
            logger.error("User can't be null.");
            throw new IllegalArgumentException("User can't be null.");
        }
    }

    private void validateLocation(Location location) {
        if (location == null) {
            logger.error("Location can't be null.");
            throw new IllegalArgumentException("Location can't be null.");
        }
    }

    private void validateLocationName(String name) {
        if (name == null) {
            logger.error("LocationName can't be null.");
            throw new IllegalArgumentException("LocationName can't be null.");
        }
    }

    private void validateItemName(String name) {
        if (name == null) {
            logger.error("ItemName can't be null.");
            throw new IllegalArgumentException("ItemName can't be null.");
        }
    }
}
