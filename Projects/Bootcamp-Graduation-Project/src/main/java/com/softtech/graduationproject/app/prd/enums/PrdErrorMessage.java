package com.softtech.graduationproject.app.prd.enums;

import com.softtech.graduationproject.app.gen.enums.BaseErrorMessage;

public enum PrdErrorMessage implements BaseErrorMessage {

    PRODUCT_NOT_FOUND("Product not found!"),
    FIELD_CANNOT_BE_NULL("Entered field cannot be null!"),

    VAT_FREE_PRICE_CANNOT_BE_NULL("VAT free price cannot be null!"),
    PRICE_CANNOT_BE_NULL("Price cannot be null!"),
    PRODUCT_TYPE_CANNOT_BE_NULL("Product type cannot be null!"),
    ID_VALUES_CANNOT_BE_NULL("Id values cannot be null!"),

    PRICE_MUST_BE_POSITIVE("Price must be positive!"),
    FIELD_MUST_BE_POSITIVE("Entered field must be positive!"),

    PARAMETER_MIN_CANNOT_BE_LARGER_THAN_MAX("Parameter min cannot be larger than max!")
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