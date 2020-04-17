package com.facundo.overriding;

public class OverridingMain {
    public static void main(String[] args) {
        Person personOne = new Person();
        personOne.setName("John");

        Person personTwo = new Person();
        personTwo.setName("John");

        if (personOne.equals(personTwo)) {
            System.out.println("They are the same person.");
        } else {
            System.out.println("They are NOT the same person.");
        }
    }
}
