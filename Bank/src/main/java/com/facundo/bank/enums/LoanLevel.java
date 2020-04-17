package com.facundo.bank.enums;

import java.math.BigDecimal;

public enum LoanLevel {
    LOW(BigDecimal.valueOf(2500)),
    MEDIUM_LOW(BigDecimal.valueOf(7500)),
    MEDIUM(BigDecimal.valueOf(15000)),
    MEDIUM_HIGH(BigDecimal.valueOf(20000)),
    HIGH(BigDecimal.valueOf(30000));

    private BigDecimal loan;

    LoanLevel(BigDecimal loan){
        this.loan = loan;
    }

    public BigDecimal getLoan() {
        return this.loan;
    }
}
