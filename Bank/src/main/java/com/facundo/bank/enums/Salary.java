package com.facundo.bank.enums;

import java.math.BigDecimal;

public enum Salary {
    ANALYST(BigDecimal.valueOf(5800)),
    ASSOCIATE(BigDecimal.valueOf(16000)),
    MANAGING_DIRECTOR(BigDecimal.valueOf(30000)),
    VICE_PRESIDENT(BigDecimal.valueOf(25000));

    private BigDecimal salary;

    Salary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }
}
