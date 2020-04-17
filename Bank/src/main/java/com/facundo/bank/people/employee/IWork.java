package com.facundo.bank.people.employee;

import com.facundo.bank.banks.AbstractBank;
import com.facundo.bank.people.clients.Client;

import java.math.BigDecimal;

public interface IWork {
    void createPitchBook();
    void createModel();
    void createProjections();
    boolean doValuation(Client client, AbstractBank bank);
}