package com.facundo.bank.people.employee;

import com.facundo.bank.banks.AbstractBank;
import com.facundo.bank.enums.Salary;
import com.facundo.bank.people.Person;
import com.facundo.bank.people.clients.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class Associate extends Person implements IWork{

    private final Logger LOGGER = LogManager.getLogger(Associate.class);

    private Salary salary = Salary.ASSOCIATE;

    public Associate() {
    }

    public BigDecimal getSalary() {
        return salary.getSalary();
    }

    @Override
    public void createPitchBook() {

    }

    @Override
    public void createModel() {

    }

    @Override
    public void createProjections() {

    }

    @Override
    public boolean doValuation(Client client, AbstractBank bank) {
        boolean isClient = bank.getClients().stream().anyMatch(c -> c.equals(client));
        if (isClient) {
            LOGGER.error("It's already a client of the bank.");
            return false;
        }
        if (bank.getClass().getSimpleName().equals("PrivateBank")) {
            if (client.getSalary().compareTo(BigDecimal.valueOf(1000)) >= 0) {
                LOGGER.info("The client satisfy the required salary.");
                return true;
            } else {
                LOGGER.info("The client doesn't satisfy the required salary.");
                return false;
            }
        } else {
            if (client.getSalary().compareTo(BigDecimal.valueOf(300)) >= 0) {
                LOGGER.info("The client satisfy the required salary.");
                return true;
            } else {
                LOGGER.info("The client doesn't satisfy the required salary.");
                return false;
            }
        }
    }

    private void controlVicePresidentWork(VicePresident vicePresident){
    }
}
