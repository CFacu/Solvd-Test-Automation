package com.facundo.bank.banks;

import com.facundo.bank.people.clients.Client;

import java.math.BigDecimal;

public class PrivateBank extends AbstractBank {

    private static final BigDecimal MINIMUM_LOAN = BigDecimal.valueOf(1000);

    public PrivateBank(){}

    public static BigDecimal getMINIMUM_LOAN() {
        return MINIMUM_LOAN;
    }

    public void newClient(Client client) {
        this.getManagingDirector().newClient(client, this);
        this.addClient(client);
        this.addAccount(client.getBankAccount());
    }
}