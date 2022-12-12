package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (head == null) {
            head = newNode;

        } else {
            Node<E> bufNode = head;
            while (bufNode.next != null) {
               bufNode = bufNode.next;
            }
            bufNode.next = newNode;
        }

        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> bufNode = head;
        for (int i = 0; i < index; i++) {
            bufNode = bufNode.next;
        }
        return index == 0 ? head.item : bufNode.item;
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
