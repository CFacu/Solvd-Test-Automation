package com.facundo.linkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(5);
        list.add(98);
        list.add(88);
        list.add(15);

        LOGGER.info("First list.");
        list.show();

        list.remove(2);
        list.addAtStart(55);

        LOGGER.info("Second list");
        list.show();

        list = list.reverse();
        LOGGER.info("Reverse list");
        list.show();
    }
}
