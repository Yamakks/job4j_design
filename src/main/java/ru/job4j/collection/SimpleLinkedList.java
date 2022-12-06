package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    public SimpleLinkedList() {
    }

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
           head.next = newNode;
        }
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        int ind = 0;
        Node<E> current = head;
        while (ind != index) {
            current = current.next;
            ind++;
        }
        return current.item;
        }


    @Override
    public Iterator<E> iterator() {
        return null;
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
