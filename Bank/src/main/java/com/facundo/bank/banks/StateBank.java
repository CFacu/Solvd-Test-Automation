package com.facundo.bank.banks;

import com.facundo.bank.people.clients.Client;

import java.math.BigDecimal;

public class StateBank extends AbstractBank {

    private static final BigDecimal MINIMUM_LOAN = BigDecimal.valueOf(350);

    public StateBank() {}

    public static BigDecimal getMINIMUM_LOAN() {
        return MINIMUM_LOAN;
    }

    public void newClient(Client client) {
        this.getManagingDirector().newClient(client, this);
        this.addClient(client);
        this.addAccount(client.getBankAccount());
    }
}
