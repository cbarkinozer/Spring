package com.softtech.softtechspringboot.str.enums;

import com.softtech.softtechspringboot.gen.enums.BaseErrorMessage;

public enum StrErrorMessage implements BaseErrorMessage {
    STREET_ERROR_MESSAGE("Street not found!")
    ;

    private String message;

    StrErrorMessage(String message){
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
