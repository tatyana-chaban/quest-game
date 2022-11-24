package com.javarush.tchaban.questgame.engine.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Location {
    private final String name;
    private boolean isLocked;

    @Builder.Default
    private final List<String> availableLocations = new ArrayList<>();
    @Builder.Default
    private final List<String> items = new ArrayList<>();

}
