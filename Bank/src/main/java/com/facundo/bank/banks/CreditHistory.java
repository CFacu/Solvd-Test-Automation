package com.facundo.bank.banks;

import com.facundo.bank.exceptions.LoanNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CreditHistory {
    private final Logger LOGGER = LogManager.getLogger(CreditHistory.class);

    private List<Loan> loans = new ArrayList<>();

    public CreditHistory() {
    }

    public CreditHistory(List<Loan> loans) {
        this.loans = loans;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public void addLoan(Loan loan){
        loans.add(loan);
    }

    public Loan getLoan(Integer number) throws LoanNotFoundException{
        if (loans.stream().anyMatch(l -> l.getNumber().equals(number))) {
            return loans.stream().filter(a -> a.getNumber().equals(number)).findAny().get();
        } else throw new LoanNotFoundException("Loan not found.");
    }
}