package com.javarush.tchaban.questgame.engine.repository;

import java.util.*;

public class Repository <K, T>{
    private final Map<K, T> repository = new HashMap<>();

    public void add(K key, T value){
        if (key == null){
            throw new IllegalArgumentException("Key can't be null.");
        }
        if (value == null){
            throw new IllegalArgumentException("Value can't be null.");
        }

        repository.put(key, value);
    }

    public T getByKey(K key){
        if (key == null){
            throw new IllegalArgumentException("Key can't be null.");
        }
        return repository.get(key);
    }

    public boolean contains(K key){
        if (key == null){
            throw new IllegalArgumentException("Key can't be null.");
        }
        return repository.containsKey(key);
    }

    public Set<K> getAllKeys(){
        return repository.keySet();
    }
}
