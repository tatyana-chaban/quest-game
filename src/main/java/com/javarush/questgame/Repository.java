package com.javarush.questgame;

import java.util.HashMap;
import java.util.Map;

public class Repository <K, T>{
    private final Map<K, T> repository = new HashMap<>();

    public void add(K key, T value){
        repository.put(key, value);
    }

    public T getByKey(K key){
        return repository.get(key);
    }

    public boolean contains(K key){
        return repository.containsKey(key);
    }

    public boolean isEmpty(){
        return repository.isEmpty();
    }
}
