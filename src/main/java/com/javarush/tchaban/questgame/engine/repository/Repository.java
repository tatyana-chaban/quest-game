package com.javarush.tchaban.questgame.engine.repository;

import java.util.*;

public class Repository<K, T> {
    private final Map<K, T> repository = new HashMap<>();

    public void add(K key, T value) {
        validateKey(key);
        validateValue(value);

        repository.put(key, value);
    }

    public T getByKey(K key) {
        validateKey(key);

        return repository.get(key);
    }

    public boolean contains(K key) {
        validateKey(key);

        return repository.containsKey(key);
    }

    public Set<K> getAllKeys() {
        return repository.keySet();
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null.");
        }
    }

    private void validateValue(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value can't be null.");
        }
    }
}
