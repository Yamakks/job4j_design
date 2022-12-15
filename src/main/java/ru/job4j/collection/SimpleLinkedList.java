package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    @Override
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

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> bufNode = head;
        for (int i = 1; i <= index; i++) {
            bufNode = bufNode.next;
        }
        return bufNode.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> bufNode = head;
            int mod = modCount;


            @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return bufNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> bufNodenext = bufNode;
                bufNode = bufNode.next;
                return bufNodenext.item;
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
