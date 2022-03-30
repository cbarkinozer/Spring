package com.softtech.graduationproject.app.vrt.enums;

import com.softtech.graduationproject.app.gen.enums.BaseErrorMessage;

public enum VrtErrorMessage implements BaseErrorMessage {

    VAT_RATE_NOT_FOUND("VAT rate not found!"),
    VAT_RATE_CANNOT_BE_NEGATIVE("VAT rate cannot be negative!"),
    VAT_RATE_CANNOT_BE_NULL("VAT rate cannot be null!"),
    VAT_RATE_ALREADY_EXIST("VAT rate already exist!"),
    FIELDS_CANNOT_BE_NULL("Fields cannot be null!"),
    ;

    private String message;

    VrtErrorMessage(String message) {
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
