package com.softtech.softtechspringboot.usr.enums;

import com.softtech.softtechspringboot.gen.enums.BaseErrorMessage;

public enum UsrErrorMessage implements BaseErrorMessage {

    USER_NOT_FOUND("User not found!"),
    USERNAME_ALREADY_EXISTS("Username of the user already exists!"),
    EMAIL_ALREADY_EXISTS("Email of the user already exists!"),
    PHONE_NUMBER_ALREADY_EXISTS("Phone number of the user already exists!"),
    UNMATCHED_USERNAME_AND_PHONE_NUMBER("Username and phone number do not match")
    ;

    private String message;
    UsrErrorMessage(String message) {
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
