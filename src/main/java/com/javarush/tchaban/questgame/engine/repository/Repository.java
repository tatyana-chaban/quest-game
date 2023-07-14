package com.javarush.tchaban.questgame.engine.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Repository<K, T> {
    private static final Logger logger = LoggerFactory.getLogger(Repository.class);

    private final Map<K, T> repository = new HashMap<>();

    public void add(K key, T value) {
        validateParam(key, "Key");
        validateParam(value, "Value");

        repository.put(key, value);
    }

    public T getByKey(K key) {
        validateParam(key, "Key");

        return repository.get(key);
    }

    public boolean contains(K key) {
        validateParam(key, "Key");
        return repository.containsKey(key);
    }

    public Set<K> getAllKeys() {
        return repository.keySet();
    }

    private <P> void validateParam(P param, String paramName) {
        if (param == null) {
            logger.error(paramName + " can't be null.");
            throw new IllegalArgumentException(paramName + " can't be null.");
        }
    }
}
