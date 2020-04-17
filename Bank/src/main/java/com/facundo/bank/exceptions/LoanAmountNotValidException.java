package com.facundo.bank.exceptions;

import java.io.IOException;

public class LoanAmountNotValidException extends IOException {
    public LoanAmountNotValidException(String message){
        super(message);
    }
}
