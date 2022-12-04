package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = null;
        if (Objects.checkIndex(index, container.length) == index) {
            oldValue = container[index];
            container[index] = newValue;
            modCount++;
        }
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T delValue = null;
        if (Objects.checkIndex(index, container.length) == index) {
          delValue = container[index];
          System.arraycopy(container,
                  index + 1,
                  container,
                  index,
                  container.length - index - 1);
          container[container.length - 1] = null;
          modCount++;
        }
        return delValue;
    }

    @Override
    public T get(int index) {
        return (Objects.checkIndex(index, container.length) == index) ? container[index] : null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
