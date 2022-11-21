package com.javarush.questgame;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Location {
    private final String name;

    @Builder.Default
    private final List<String> availableLocations = new ArrayList<>();
    @Builder.Default
    private final List<String> nps = new ArrayList<>();
    @Builder.Default
    private final List<String> items = new ArrayList<>();

}
