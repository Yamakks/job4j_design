package ru.job4j.map;

import ru.job4j.collection.ForwardLinked;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result;
        int hk = key == null ? 0 : hash(key.hashCode());
        if (table[indexFor(hk)] != null) {
            result = false;
        } else {
            table[indexFor(hk)] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
            expand();
        }

        return result;
    }

    @Override
    public V get(K key) {
        int hk = key == null ? 0 : hash(key.hashCode());
        MapEntry<K, V> mapCell = table[indexFor(hk)];
        K tableKey = mapCell.key;
        V result;
        if (mapCell == null || key != tableKey) {
            result = null;
        } else {
            result = mapCell.value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result;

        int hk = key == null ? 0 : hash(key.hashCode());
        K tableKey = table[indexFor(hk)].key;
        if (tableKey == key) {
            result = true;
            table[indexFor(hk)] = null;
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
        MapEntry<K, V>[] bufMap = table;
        int mod = modCount;
        int index = 0;

        @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
            while (index < capacity && bufMap[index] == null) {
                index++;
            }
                return  index < capacity;
            }
        @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return bufMap[index++].key;
            }
        };
    }


    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        if ((float) count / capacity > LOAD_FACTOR) {
            capacity = capacity * 2;
            MapEntry<K, V>[] newTable = new MapEntry[capacity];
            for (MapEntry<K, V> sockets : table) {
                if (sockets != null) {
                    int hk = hash(sockets.key.hashCode());
                    newTable[indexFor(hk)] = new MapEntry<>(sockets.key, sockets.value);
                }
            }
            table = newTable;
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
