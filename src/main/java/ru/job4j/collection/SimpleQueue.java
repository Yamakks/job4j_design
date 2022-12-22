package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (in == null) {
            throw new NoSuchElementException();
        }
        return in.pop();
    }

    public void push(T value) {
        while (out == null) {
            out.push(value);

        }
    }
}
