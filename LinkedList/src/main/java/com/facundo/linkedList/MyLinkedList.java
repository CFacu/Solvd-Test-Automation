package com.facundo.linkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<Node>{
    private final Logger LOGGER = LogManager.getLogger(MyLinkedList.class);
    private Node<T> first;
    private Node<T> last;

    public MyLinkedList() {
        first = last = null;
    }

    public Node<T> getLast() {
        return last;
    }

    public Node<T> getFirst() {
        return first;
    }

    public void add(T data) {
        Node<T> node = new Node<T>();
        node.setData(data);
        node.setNext(null);

        if (first == null) {
            node.setPrev(null);
            first = last = node;
        } else {
            node.setPrev(last);
            last.setNext(node);
            last = node;
        }
    }

    public void add(int index, T data) {
        Node<T> node = new Node<T>();
        node.setData(data);
        node.setNext(null);
        node.setPrev(null);
        Node<T> n = first;

        if (index == 0) addAtStart(data);
        else {
            for (int i = 0; i < index - 1; i++) {
                n = n.getNext();
            }
            Node<T> aux = n.getNext();
            node.setNext(aux);
            aux.setPrev(node);
            node.setPrev(n);
            n.setNext(node);
        }
    }

    public void addAtStart(T data) {
        Node<T> node = new Node<T>();
        node.setData(data);
        node.setNext(first);
        node.setPrev(null);
        first.setPrev(node);
        first = node;
    }

    public void addAtEnd(T data) {
        Node<T> node = new Node<T>();
        node.setData(data);
        node.setNext(null);
        node.setPrev(last);
        last.setNext(node);
        last = node;
    }

    public void remove(int index) {
        if (index == 0) {
            first = first.getNext();
        }else {
            Node<T> node = first;
            Node<T> n;
            for (int i = 0; i < index-1; i++) {
                node = node.getNext();
            }
            n = node.getNext();
            n.getNext().setPrev(node);
            node.setNext(n.getNext());
            n = null;
        }
    }

    public void show() {
        for (Node n : this) {
            LOGGER.info(n.getData());
        }
    }

    public MyLinkedList<T> reverse() {
        MyLinkedList<T> newList = new MyLinkedList<>();
        Node<T> node = last;
        while(node.getPrev() != null) {
            newList.add(node.getData());
            node = node.getPrev();
        }
        newList.add(node.getData());
        return newList;
    }

    @Override
    public Iterator<Node> iterator() {
        return new ListIterator(first);
    }
}
