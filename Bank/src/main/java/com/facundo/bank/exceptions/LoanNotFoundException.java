package com.facundo.bank.exceptions;

import java.io.IOException;

public class LoanNotFoundException extends IOException {
    public LoanNotFoundException(String message) {
        super(message);
    }
}
