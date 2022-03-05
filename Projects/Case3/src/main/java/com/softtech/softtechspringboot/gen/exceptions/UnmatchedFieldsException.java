package com.softtech.softtechspringboot.gen.exceptions;

import com.softtech.softtechspringboot.gen.enums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UnmatchedFieldsException extends RuntimeException{
    public UnmatchedFieldsException(BaseErrorMessage message){super(message.getMessage());}
}
