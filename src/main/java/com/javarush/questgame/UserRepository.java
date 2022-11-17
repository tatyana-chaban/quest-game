package com.javarush.questgame;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> repository;

    public UserRepository(){
        repository = new ArrayList<>();
    }

    public boolean contains(User user){
        return repository.contains(user);
    }

    public void add(User user){
        repository.add(user);
    }
}
