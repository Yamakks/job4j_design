package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<E> implements Iterable<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        size++;
        modCount++;
        if (head == null) {
            head = newNode;

        } else {
            Node<E> bufNode = head;
            while (bufNode.next != null) {
                bufNode = bufNode.next;
            }
            bufNode.next = newNode;
        }
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> bufNode = head;
        for (int i = 0; i < index; i++) {
            bufNode = bufNode.next;
        }
        return index == 0 ? head.item : bufNode.item;
    }

    public E deleteFirst() {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int index = 0;
            int mod = modCount;

            @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> bufNode = head;
                bufNode = index == 0 ? bufNode : bufNode.next;
                index++;
                return bufNode.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
