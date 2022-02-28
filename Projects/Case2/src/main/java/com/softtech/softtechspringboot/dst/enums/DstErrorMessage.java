package com.softtech.softtechspringboot.dst.enums;

import com.softtech.softtechspringboot.gen.enums.BaseErrorMessage;

public enum DstErrorMessage implements BaseErrorMessage {

    DISTRICT_ERROR_MESSAGE("District not found!")
    ;

    private String message;

    DstErrorMessage(String message){
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
