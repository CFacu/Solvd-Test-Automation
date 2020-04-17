package com.facundo.linkedList;

import java.util.Iterator;

public class ListIterator implements Iterator<Node> {
    private Node current;

    public ListIterator(Node first) {
        current = first;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Node next() {
        Node temp = current;
        current = current.getNext();
        return temp;
    }
}
