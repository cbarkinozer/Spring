package com.softtech.graduationproject.app.gen.exceptions;

import com.softtech.graduationproject.app.gen.enums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemAlreadyExistsException extends GenBusinessException{
    public ItemAlreadyExistsException(BaseErrorMessage message) {
        super(message);
    }
}
