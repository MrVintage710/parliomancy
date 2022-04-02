package me.mrvintage.kingdom.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DepthMap<K, V> {

    private HashMap<K, ArrayList<V>> map = new HashMap<>();

    public void put(K key, V value) {
        checkKey(key);
        map.get(key).add(value);
    }

    public ArrayList<V> get(K key) {
        return map.get(key);
    }

    public V get(K key, int index) {
        return map.get(key).get(index);
    }

    public V first(K key) {
        return map.get(key).get(0);
    }

    public boolean contains(K key) {
        return map.containsKey(key);
    }

    private void checkKey(K key) {
        if(!map.containsKey(key)) map.put(key, new ArrayList<>());
    }
}
