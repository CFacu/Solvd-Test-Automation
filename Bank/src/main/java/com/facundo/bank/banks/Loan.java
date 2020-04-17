package com.facundo.bank.banks;

import com.facundo.bank.people.clients.Client;

import java.math.BigDecimal;
import java.util.Objects;

public class Loan {
    private BigDecimal amount;
    private Integer number;
    private Short interestRate;
    private Boolean isClosed;
    private Client client;

    public Loan() {
        this.isClosed = false;
    }

    public Loan(BigDecimal amount, Integer number, Short interestRate, Boolean isClosed, Client client) {
        this.amount = amount;
        this.number = number;
        this.interestRate = interestRate;
        this.isClosed = isClosed;
        this.client = client;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Short getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Short interestRate) {
        this.interestRate = interestRate;
    }

    public Boolean getClosed() {
        return isClosed;
    }

    public void setClosed(Boolean closed) {
        this.isClosed = closed;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.number);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Loan loan = (Loan) obj;
        return this.number == loan.getNumber() && number.equals(loan.getNumber());
    }

    @Override
    public String toString() {
        return "Loan number: " + this.number.toString() +
                "\tAmount: " + this.amount.toString();
    }
}
