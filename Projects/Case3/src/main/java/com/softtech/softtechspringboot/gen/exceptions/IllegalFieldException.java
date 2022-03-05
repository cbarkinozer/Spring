package com.softtech.softtechspringboot.gen.exceptions;

import com.softtech.softtechspringboot.gen.enums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IllegalFieldException extends RuntimeException{
    public IllegalFieldException(BaseErrorMessage message){
        super(message.getMessage());
    }

    public IllegalFieldException(String username,String phoneNumber, BaseErrorMessage message){
        super(message.getMessage()+" for username: "+username+" and phone number: "+phoneNumber);
    }

}
