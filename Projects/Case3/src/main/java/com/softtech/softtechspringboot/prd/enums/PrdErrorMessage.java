package com.softtech.softtechspringboot.prd.enums;

import com.softtech.softtechspringboot.gen.enums.BaseErrorMessage;

public enum PrdErrorMessage implements BaseErrorMessage {

    PRODUCT_NOT_FOUND("Product not found!"),
    ;

    private String message;
    PrdErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}