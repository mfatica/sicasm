package com.mfatica;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LookupTable<K, V> {
    private Map<K, V> _map;

    public LookupTable() {
        this._map = new HashMap<K, V>();
    }

    public V get(K key) {
        return _map.get(key);
    }

    public void set(K key, V value) {
        if (_map.containsKey(key)) {
            _map.replace(key, value);
        } else {
            _map.put(key, value);
        }
    }

    public boolean containsKey(K key) {
        return _map.containsKey(key);
    }
}
