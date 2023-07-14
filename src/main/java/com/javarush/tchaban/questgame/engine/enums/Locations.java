package com.javarush.tchaban.questgame.engine.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Locations {
    HALL("hall"),
    KITCHEN("kitchen"),
    BATHROOM("bathroom"),
    STAIRS("stairs"),
    LABORATORY("laboratory"),
    BEDROOM("bedroom");

    private final String name;
}
