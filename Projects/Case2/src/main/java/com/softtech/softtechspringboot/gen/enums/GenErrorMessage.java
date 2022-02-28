package com.softtech.softtechspringboot.gen.enums;

public enum GenErrorMessage implements BaseErrorMessage{

    ITEM_NOT_FOUND("Item not found!"),
    ;

    private String message;
    GenErrorMessage(String message) {
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
