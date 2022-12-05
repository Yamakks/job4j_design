package ru.job4j.collection;

import java.util.*;

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
        if (container.length == 0) {
            container = Arrays.copyOf(container, container.length + 1);
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
            size--;
            modCount++;
        }
        return delValue;
    }

    @Override
    public T get(int index) {
        if (Objects.checkIndex(index, container.length) == index && index > size) {
            throw new IndexOutOfBoundsException();
        }
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            int mod = modCount;

            @Override
            public boolean hasNext() {
                while (index > container.length) {
                    index++;
                }
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (index < container.length && size > 0);
            }

            @Override
            public T next() {
                if (!hasNext() || size == 0) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}
