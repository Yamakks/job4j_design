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
        growUpCapacity();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue;
        index = Objects.checkIndex(index, size);
            oldValue = container[index];
            container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T delValue;
        index = Objects.checkIndex(index, size);
            delValue = container[index];
            System.arraycopy(container,
                    index + 1,
                    container,
                    index,
                    container.length - index - 1);
            container[container.length - 1] = null;
            size--;
            modCount++;
        return delValue;
    }

    @Override
    public T get(int index) {
        index = Objects.checkIndex(index, size);
        if (index > size) {
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
        return new Iterator<>() {
            int index = 0;
            int mod = modCount;

            @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (index < size && size > 0);
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
    void growUpCapacity() {
        if (container.length == size) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        if (container.length == 0) {
            container = Arrays.copyOf(container, 10);
        }
    }
}
