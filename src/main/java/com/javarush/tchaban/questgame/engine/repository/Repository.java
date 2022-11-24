package com.javarush.tchaban.questgame.engine.repository;

import java.util.*;

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

    public Set<K> getAllKeys(){
        return repository.keySet();
    }
}
