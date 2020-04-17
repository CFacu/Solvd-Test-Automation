package com.facundo.bank.people.clients;

import com.facundo.bank.exceptions.LoanAmountNotValidException;

import java.math.BigDecimal;

public interface ITransaction {
    void makeDeposit(BigDecimal amount);
    void withdrawal(BigDecimal amount);
    void payLoan(BigDecimal amount, Integer loanNumber);
}