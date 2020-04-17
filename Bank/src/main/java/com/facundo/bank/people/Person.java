package com.facundo.bank.people;

import com.facundo.bank.enums.Salary;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Person {
    private String name;
    private String lastName;
    private Integer age;
    private BigDecimal cash;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        Person employee = (Person) object;
        return this.name == employee.getName() && this.lastName == employee.getLastName() && name.equals(employee.getName()) && lastName.equals(employee.getLastName());
    }
}
