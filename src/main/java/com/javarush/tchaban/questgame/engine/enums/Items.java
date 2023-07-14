package com.javarush.tchaban.questgame.engine.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Items {
    REAGENT("bottle with reagent"),
    FORMULA("secret formula"),
    STAR("star"),
    KEY("key"),
    VIRUS("test tube with virus");

    private final String name;
}
