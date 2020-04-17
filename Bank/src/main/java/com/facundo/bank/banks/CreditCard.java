package com.facundo.bank.banks;

import java.math.BigDecimal;
import java.util.Date;

public class CreditCard {
    private Integer number;
    private BigDecimal limit;
    private Date expDate;

    public CreditCard() {
    }

    public CreditCard(Integer number, BigDecimal limit, Date expDate) {
        this.number = number;
        this.limit = limit;
        this.expDate = expDate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
}
