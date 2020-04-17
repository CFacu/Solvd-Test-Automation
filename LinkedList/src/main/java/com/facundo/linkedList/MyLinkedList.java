package com.facundo.linkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<Node>{
    private final Logger LOGGER = LogManager.getLogger(MyLinkedList.class);
    private Node first;

    public void add(T data) {
        Node<T> node = new Node<T>();
        node.setData(data);
        node.setNext(null);

        if (first == null) {
            first = node;
        } else {
            Node n = first;
            while (n.getNext() != null) {
                n = n.getNext();
            }
            n.setNext(node);
        }
    }

    public void add(int index, T data) {
        Node<T> node = new Node<T>();
        node.setData(data);
        node.setNext(null);
        Node n = first;

        if (index == 0) addAtStart(data);
        else {
            for (int i = 0; i < index - 1; i++) {
                n = n.getNext();
            }
            node.setNext(n.getNext());
            n.setNext(node);
        }
    }

    public void addAtStart(T data) {
        Node<T> node = new Node<T>();
        node.setData(data);
        node.setNext(first);
        first = node;
    }

    public void remove(int index) {
        if (index == 0) {
            first = first.getNext();
        }else {
            Node node = first;
            Node n;
            for (int i = 0; i < index-1; i++) {
                node = node.getNext();
            }
            n = node.getNext();
            node.setNext(n.getNext());
            n = null;
        }
    }

    public void show() {
        for (Node n : this) {
            LOGGER.info(n.getData());
        }
    }

    public MyLinkedList reverse() {
        MyLinkedList newList = new MyLinkedList<>();
        Node node = first;
        while (node.getNext() != null) {
            newList.addAtStart(node.getData());
            node = node.getNext();
        }
        newList.addAtStart(node.getData());
        return newList;
    }

    @Override
    public Iterator<Node> iterator() {
        return new ListIterator(first);
    }
}
