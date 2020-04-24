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
        list.add(20);
        list.add(29);

        list.add(5,56);

        list.addAtStart(1);

        list.addAtEnd(99);

        LOGGER.info("First List:");
        list.show();

        LOGGER.info("\n");
        LOGGER.info("Reversed List:");
        list = list.reverse();

        list.show();

    }
}
