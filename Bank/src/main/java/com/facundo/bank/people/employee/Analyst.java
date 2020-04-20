package com.facundo.bank.people.employee;

import com.facundo.bank.banks.AbstractBank;
import com.facundo.bank.banks.Loan;
import com.facundo.bank.banks.PrivateBank;
import com.facundo.bank.banks.StateBank;
import com.facundo.bank.enums.Salary;
import com.facundo.bank.exceptions.LoanAmountNotValidException;
import com.facundo.bank.lambdas.ISearch;
import com.facundo.bank.people.Person;
import com.facundo.bank.people.clients.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class Analyst extends Person implements IWork {
    private final Logger LOGGER = LogManager.getLogger(Analyst.class);

    private Salary salary = Salary.ANALYST;

    public Analyst() {

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

    public void newLoan(Client client, BigDecimal amount, Short rate, Integer number) throws LoanAmountNotValidException {
        List<Loan> loanHistory = client.getCreditHistory().getLoans();
        for (Loan l : loanHistory) {
            if (l.getClosed().equals(false)) {
                LOGGER.error("This client already has an active loan.");
                return;
            }
        }

        if (amount.compareTo(BigDecimal.valueOf(1000)) < 0){
            throw new LoanAmountNotValidException("Loan amount have to be 1000 or greater.");
        } else {
            client.getBankAccount().setBalance(client.getBankAccount().getBalance().add(amount));
            amount = amount.add(BigDecimal.valueOf(amount.intValue() * rate / 100));
            Loan loan = new Loan(amount, number, rate, false, client);
            client.getCreditHistory().addLoan(loan);
        }
    }

    @Override
    public boolean doValuation(Client client, AbstractBank bank) {
        for (Client c : bank.getClients()) {
            if (c.equals(client)) {
                LOGGER.error("It's already a client of the bank.");
                return false;
            }
        }
        if (bank.getClass().getSimpleName().equals("PrivateBank")) {
            if (client.getSalary().compareTo(PrivateBank.getMINIMUM_LOAN()) >= 0) {
                LOGGER.info("The client satisfy the required salary.");
                return true;
            } else {
                LOGGER.info("The client doesn't satisfy the required salary.");
                return false;
            }
        } else {
            if (client.getSalary().compareTo(StateBank.getMINIMUM_LOAN()) >= 0) {
                LOGGER.info("The client satisfy the required salary.");
                return true;
            } else {
                LOGGER.info("The client doesn't satisfy the required salary.");
                return false;
            }
        }
    }
}