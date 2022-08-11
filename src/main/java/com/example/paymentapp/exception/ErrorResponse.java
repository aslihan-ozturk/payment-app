package com.example.paymentapp.exception;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse( String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
