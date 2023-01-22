package ru.job4j.map;

import java.util.Iterator;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result;
        int hk = hash(key.hashCode());
        if (table[indexFor(hk)] != null) {
            result = false;
        } else {
            table[indexFor(hk)] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }

        return result;
    }

    @Override
    public V get(K key) {
        int hk = hash(key.hashCode());
        return table[indexFor(hk)] == null ? null : table[indexFor(hk)].value;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        if ((float) (count / capacity) >= LOAD_FACTOR) {
            capacity *= 2;
        }
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
