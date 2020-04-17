package com.facundo.repeatedwords;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            RepeatedWord.counter();
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
