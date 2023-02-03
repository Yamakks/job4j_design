package ru.job4j.map;

import ru.job4j.collection.ForwardLinked;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int hKey(K key) {
        return key == null ? 0 : hash(key.hashCode());
    }
    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hKey(key));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }

        return result;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hKey(key));
        return table[index] != null && Objects.equals(hKey(key), hKey(table[index].key))
                && Objects.equals(key, table[index].key) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean result;
        int hk = hKey(key);
        int index = indexFor(hk);
        if (table[index] != null && Objects.equals(hKey(key), hKey(table[index].key))
                && Objects.equals(key, table[index].key)) {
            result = true;
            table[index] = null;
            modCount++;
            count--;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
        final int mod = modCount;
        int index = 0;

        @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
            while (index < capacity && table[index] == null) {
                index++;
            }
                return  index < capacity;
            }
        @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }


    private int hash(int hashCode) {
        return hashCode;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
            capacity = capacity * 2;
            MapEntry<K, V>[] newTable = new MapEntry[capacity];
            for (MapEntry<K, V> sockets : table) {
                if (sockets != null) {
                    int index = indexFor(hKey(sockets.key));
                    newTable[indexFor(index)] = sockets;
                }
            }
            table = newTable;
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
