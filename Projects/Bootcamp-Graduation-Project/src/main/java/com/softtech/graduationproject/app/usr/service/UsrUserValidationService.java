package com.softtech.graduationproject.app.usr.service;

import com.softtech.graduationproject.app.gen.entity.BaseEntity;
import com.softtech.graduationproject.app.gen.exceptions.IllegalFieldException;
import com.softtech.graduationproject.app.gen.exceptions.ItemAlreadyExistsException;
import com.softtech.graduationproject.app.gen.exceptions.ItemNotFoundException;
import com.softtech.graduationproject.app.usr.dto.UsrUserUpdateRequestDto;
import com.softtech.graduationproject.app.usr.entity.UsrUser;
import com.softtech.graduationproject.app.usr.enums.UsrErrorMessage;
import com.softtech.graduationproject.app.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsrUserValidationService {


    private final UsrUserEntityService usrUserEntityService;


    public void controlIsUserExist(Long id) {

        boolean isExist = usrUserEntityService.existsById(id);

        if (!isExist){

            throw new ItemNotFoundException(UsrErrorMessage.USER_NOT_FOUND);
        }
    }


    public void controlAreFieldsNonNull(UsrUser usrUser){

        boolean hasNullField =
                usrUser.getName().isBlank()     ||
                usrUser.getSurname().isBlank()  ||
                usrUser.getUsername().isBlank() ||
                usrUser.getPassword().isBlank();

        if(hasNullField){

            throw new IllegalFieldException(UsrErrorMessage.FIELD_CANNOT_BE_NULL);
        }
    }


    /*It does not throw exception when not present because that is the best case behaviour.*/
    public void controlIsUsernameUnique(UsrUser usrUser){

        Optional<UsrUser> usrUserOptional = usrUserEntityService.findUsersByUsername(usrUser.getUsername());

        UsrUser usrUserReturned;
        if(usrUserOptional.isPresent()){

            usrUserReturned = usrUserOptional.get();

            boolean didMatchedItself = didMatchedItself(usrUserReturned, usrUser);

            if(!didMatchedItself){
                throw new ItemAlreadyExistsException(UsrErrorMessage.USERNAME_ALREADY_EXIST);
            }

        }

    }


    private Boolean didMatchedItself(UsrUser usrUserReturned, BaseEntity usrUser){

        return usrUserReturned.getId().equals(usrUser.getId());
    }


}
