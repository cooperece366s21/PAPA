package edu.cooper.ece366;

import java.util.HashMap;
import java.util.Map;

public class lobbyImpl implements lobby {

    private static final int MAX_SIZE = 4;

    private final int maxSize;
    private final Map<String, String> map;

    public lobbyImpl() {
        this.map = new HashMap<>();
        this.maxSize = 4;
    }

    public lobbyImpl(Map<String, String> map) {
        this.map = map;
        this.maxSize = 4;
    }

    public lobbyImpl(int maxSize) {
        this.maxSize = maxSize;
        this.map = new HashMap<>();
    }

    @Override
    public String get(final String key) {
        return map.get(key);
    }

    @Override
    public void put(final String key, final String value) {
        validateKeySize(key);

        map.put(key, value);
    }

    private void validateKeySize(final String key) {
        if (key.length() > maxSize) {
            throw new IllegalArgumentException("exceeded max size: " + key);
        }
    }

}