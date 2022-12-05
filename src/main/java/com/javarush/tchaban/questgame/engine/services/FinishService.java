package com.javarush.tchaban.questgame.engine.services;

import com.javarush.tchaban.questgame.engine.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinishService {
    private static final Logger logger = LoggerFactory.getLogger(FinishService.class);
    public void finishGameForThisUser(User user){
        if (user == null) {
            logger.error("User can't be null.");
            throw new IllegalArgumentException("User can't be null.");
        }
        user.incrementNumberOfParties();
        user.returnToStart();
        logger.info("User " + user.getName() + " finished game.");
    }
}
