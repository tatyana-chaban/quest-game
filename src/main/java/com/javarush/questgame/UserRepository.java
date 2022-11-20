package com.javarush.questgame;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, User> repository;

    public UserRepository(){
        repository = new HashMap<>();
    }
    public boolean contains(String userName){
        return repository.containsKey(userName);
    }
    public void add(User user){
        repository.put(user.getName(), user);
    }
    public User getUserByName(String userName){
        return repository.get(userName);
    }
}
