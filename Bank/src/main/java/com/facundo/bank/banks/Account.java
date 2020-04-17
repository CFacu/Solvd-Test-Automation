package com.facundo.bank.banks;

import java.math.BigDecimal;

public class Account {
    private Integer accountNumber;
    private BigDecimal balance;
    private AbstractBank bank;

    public Account() {
        this.balance = new BigDecimal("0");
    }

    public Account(Integer accountNumber, String balance, PrivateBank bank) {
        this.accountNumber = accountNumber;
        this.balance = new BigDecimal(balance);
        this.bank = bank;
    }

    public Account(Integer accountNumber, BigDecimal balance, StateBank bank) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bank = bank;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AbstractBank getBank() {
        return bank;
    }

    public void setBank(AbstractBank bank) {
        this.bank = bank;
    }
}
