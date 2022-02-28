package com.softtech.softtechspringboot.cty.enums;

import com.softtech.softtechspringboot.gen.enums.BaseErrorMessage;

public enum CtyErrorMessage implements BaseErrorMessage {

    CITY_ERROR_MESSAGE("City not found!")
    ;

    private String message;

    CtyErrorMessage(String message){
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
