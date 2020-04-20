package com.facundo.bank.people.clients;

import com.facundo.bank.banks.Account;
import com.facundo.bank.banks.CreditCard;
import com.facundo.bank.banks.CreditHistory;
import com.facundo.bank.banks.Loan;
import com.facundo.bank.exceptions.LoanNotFoundException;
import com.facundo.bank.people.Person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Objects;

public class Client extends Person implements ITransaction {
    private final Logger LOGGER = LogManager.getLogger(Client.class);
    private Account bankAccount = new Account();
    private CreditCard creditCard = new CreditCard();
    private CreditHistory creditHistory = new CreditHistory();
    private BigDecimal salary;
    private Integer clientId;

    public Client() {
    }

    public Client(Account bankAccount, CreditCard creditCard, CreditHistory creditHistory, BigDecimal salary, Integer clientId) {
        this.bankAccount = bankAccount;
        this.creditCard = creditCard;
        this.creditHistory = creditHistory;
        this.salary = salary;
        this.clientId = clientId;
    }

    public Account getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Account bankAccount) {
        this.bankAccount = bankAccount;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public CreditHistory getCreditHistory() {
        return creditHistory;
    }

    public void setCreditHistory(CreditHistory creditHistory) {
        this.creditHistory = creditHistory;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public void makeDeposit(BigDecimal amount) {
        bankAccount.setBalance(bankAccount.getBalance().add(amount));
        bankAccount.getBank().setReserves(bankAccount.getBank().getReserves().add(amount));
    }

    @Override
    public void withdrawal(BigDecimal amount) {
        bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
        bankAccount.getBank().setReserves(bankAccount.getBank().getReserves().subtract(amount));
    }

    @Override
    public void payLoan(BigDecimal amount, Integer loanNumber) {
        try {
            Loan loan = creditHistory.getLoan(loanNumber);
            if (loan.getClosed()) {
                LOGGER.info("The loan is closed.");
            } else {
                if (bankAccount.getBalance().compareTo(amount) < 0) {
                    LOGGER.info("The account doesn't have that amount.");
                } else {
                    loan.setAmount(loan.getAmount().subtract(amount));
                    bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
                    if (loan.getAmount().compareTo(BigDecimal.valueOf(0)) <= 0) {
                        loan.setClosed(true);
                        LOGGER.info("Loan closed.");
                    }
                }
            }
        } catch (LoanNotFoundException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return clientId == client.getClientId() && clientId.equals(client.getClientId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId);
    }
}
