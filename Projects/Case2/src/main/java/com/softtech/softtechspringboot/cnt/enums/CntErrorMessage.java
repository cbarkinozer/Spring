package com.softtech.softtechspringboot.cnt.enums;

import com.softtech.softtechspringboot.gen.enums.BaseErrorMessage;

public enum CntErrorMessage implements BaseErrorMessage {

    COUNTRY_ERROR_MESSAGE("Country not found!")
    ;

    private String message;

    CntErrorMessage(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
