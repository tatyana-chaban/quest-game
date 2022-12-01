package com.javarush.tchaban.questgame.engine.services;

import com.javarush.tchaban.questgame.engine.entities.Character;
import com.javarush.tchaban.questgame.engine.entities.Quest;
import com.javarush.tchaban.questgame.engine.entities.User;
import com.javarush.tchaban.questgame.engine.repository.Repository;
import lombok.*;

@AllArgsConstructor
public class EntranceService {
    private Repository<String, User> userRepository;
    private Repository<Integer, Quest> questRepository;
    private Character mrsStone;

    public User getOrCreateUser(String userName){
        if (userName == null || userName.isBlank()){
            throw new IllegalArgumentException("User name is null or blank.");
        }

        User user;
        if(userRepository.contains(userName)){
            user = userRepository.getByKey(userName);
        } else {
            user = new User(userName);
            userRepository.add(user.getName(), user);
            addQuestsToTheUser(user);
        }

        return user;
    }

    public Character getCharacter(){
        return mrsStone;
    }

    private void addQuestsToTheUser(User user){
        questRepository
                .getAllKeys()
                .forEach(key -> user.takeQuest(key, questRepository.getByKey(key)));
    }
}

