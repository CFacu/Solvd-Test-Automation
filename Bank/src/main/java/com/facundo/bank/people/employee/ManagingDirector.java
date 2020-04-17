package com.facundo.bank.people.employee;

import com.facundo.bank.banks.AbstractBank;
import com.facundo.bank.enums.Salary;
import com.facundo.bank.people.Person;
import com.facundo.bank.people.clients.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class ManagingDirector extends Person {

    private final Logger LOGGER = LogManager.getLogger(ManagingDirector.class);

    private Salary salary = Salary.MANAGING_DIRECTOR;

    public ManagingDirector(){
    }

    public void newClient(Client client, AbstractBank bank){
        if (bank.getAnalystsList().get(0).doValuation(client, bank)) {
            bank.addClient(client);
        }
    }

    public BigDecimal getSalary() {
        return salary.getSalary();
    }
}