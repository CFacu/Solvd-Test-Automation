package com.facundo.bank.banks;

import com.facundo.bank.Address;

public class ATM {
    private Address address;

    public ATM() {
    }

    public ATM(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
