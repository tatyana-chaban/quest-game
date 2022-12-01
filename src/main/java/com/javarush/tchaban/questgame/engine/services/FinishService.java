package com.javarush.tchaban.questgame.engine.services;

import com.javarush.tchaban.questgame.engine.entities.User;

public class FinishService {
    public void finishGameForThisUser(User user){
        if (user == null) {
            throw new IllegalArgumentException("User can't be null.");
        }
        user.incrementNumberOfParties();
        user.returnToStart();
    }
}
