package com.facundo.overriding;

import java.util.Objects;

public class Person {
    private String name;

    public void Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        Person person = (Person) object;
        return name == person.getName() && name.equals(person.getName());
    }
}
