package com.softtech.softtechspringboot.ngh.enums;

import com.softtech.softtechspringboot.gen.enums.BaseErrorMessage;

public enum NghErrorMessage implements BaseErrorMessage {
    NEIGHBORHOOD_ERROR_MESSAGE("Neighborhood not found!")
    ;

    private String message;

    NghErrorMessage(String message){
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
