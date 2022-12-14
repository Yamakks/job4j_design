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
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public E deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        E del = head.item;
        Node<E> deleted = head;
            head.item = null;
            head = deleted.next;
            deleted.next = null;
            size--;
            modCount++;
        return del;
    }

    public void addFirst(E value) {
        size++;
        modCount++;
        head = new Node<E>(value, head);
        }

    public boolean revert() {
        boolean result = true;
        if (size == 1 || head == null) {
            result = false;
        } else {
            Node<E> prev = null;
            Node<E> now = head;
            Node<E> forw;
            for (int i = 0; i < size; i++) {
                forw = now.next;
                now.next = prev;
                prev = now;
                now = forw;
                head = prev;
            }
        }
        return result;
    }
}
