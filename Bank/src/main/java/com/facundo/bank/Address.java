package com.facundo.bank;

import java.util.Objects;

public class Address {
    private String city;
    private String state;
    private String street;
    private Integer number;

    public Address() {
    }

    public Address(String city, String state, String street, Integer number) {
        this.city = city;
        this.state = state;
        this.street = street;
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;

    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.city,this.street,this.number);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Address address = (Address) obj;

        return this.city == address.getCity() && this.street == address.getStreet() && this.number == address.getNumber()
                && this.city.equals(address.getCity()) && this.number.equals(address.getNumber()) && this.street.equals(address.getStreet());

    }
}
