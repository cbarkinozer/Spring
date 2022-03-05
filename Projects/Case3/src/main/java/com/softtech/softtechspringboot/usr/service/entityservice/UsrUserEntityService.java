package com.softtech.softtechspringboot.usr.service.entityservice;


import com.softtech.softtechspringboot.gen.entity.BaseEntity;
import com.softtech.softtechspringboot.gen.exceptions.IllegalFieldException;
import com.softtech.softtechspringboot.gen.exceptions.ItemAlreadyExistsException;
import com.softtech.softtechspringboot.gen.exceptions.ItemNotFoundException;
import com.softtech.softtechspringboot.gen.service.BaseEntityService;
import com.softtech.softtechspringboot.usr.dao.UsrUserDao;
import com.softtech.softtechspringboot.usr.entity.UsrUser;
import com.softtech.softtechspringboot.usr.enums.UsrErrorMessage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsrUserEntityService extends BaseEntityService<UsrUser, UsrUserDao> {

    private final UsrUserDao usrUserDao;

    public UsrUserEntityService(UsrUserDao usrUserDao) {

        super(usrUserDao);
        this.usrUserDao=usrUserDao;
    }

    public UsrUser findByIdWithControl(Long id) {

        return usrUserDao.findById(id)
                .orElseThrow(()-> new ItemNotFoundException(UsrErrorMessage.USER_NOT_FOUND));

    }

    public UsrUser getByUsernameWithControl(String username) {

        return checkIfUserExistsByUsername(username)
                .orElseThrow(()-> new ItemNotFoundException(UsrErrorMessage.USER_NOT_FOUND));

    }

    private Optional<UsrUser> checkIfUserExistsByUsername(String username){

        return usrUserDao.findByUsername(username);

    }

    public UsrUser saveWithControl(UsrUser usrUser){

        checkIfUsernameAlreadyExists(usrUser);
        checkIfEmailAlreadyExists(usrUser);
        checkIfPhoneNumberAlreadyExists(usrUser);

        usrUserDao.save(usrUser);

        return usrUser;

    }

    private void checkIfUsernameAlreadyExists(UsrUser usrUser){

        Optional<UsrUser> usernameOptional = usrUserDao.findByUsername(usrUser.getUsername());

        boolean didMatchedItself = didMatchedItself(usernameOptional, usrUser);

        if(!didMatchedItself){
            throw new ItemAlreadyExistsException(UsrErrorMessage.USERNAME_ALREADY_EXISTS);
        }

    }

    private void checkIfEmailAlreadyExists(UsrUser usrUser){

        Optional<UsrUser> emailOptional = usrUserDao.findByEmail(usrUser.getEmail());

        boolean didMatchedItself = didMatchedItself(emailOptional, usrUser);

        if(!didMatchedItself){
            throw new ItemAlreadyExistsException(UsrErrorMessage.EMAIL_ALREADY_EXISTS);
        }
    }

    private void checkIfPhoneNumberAlreadyExists(UsrUser usrUser){

        Optional<UsrUser> phoneNumberOptional = usrUserDao.findByPhoneNumber(usrUser.getPhoneNumber());

        boolean didMatchedItself = didMatchedItself(phoneNumberOptional, usrUser);

        if(!didMatchedItself){
            throw new ItemAlreadyExistsException(UsrErrorMessage.PHONE_NUMBER_ALREADY_EXISTS);
        }
    }

    private Boolean didMatchedItself(Optional<UsrUser> optional, BaseEntity ownEntity){

        BaseEntity entity;

        if(optional.isPresent()) {

            entity = optional.get();
            return entity.getId().equals(ownEntity.getId());
            
        }else{
            return true;
        }


    }


    public UsrUser update(UsrUser usrUser) {

        checkIfUserExistById(usrUser);
        saveWithControl(usrUser);
        return usrUser;
    }

    private void checkIfUserExistById(UsrUser usrUser) {

        Long id = usrUser.getId();
        boolean isExist = usrUserDao.existsById(id);
        if (!isExist){
            throw new ItemNotFoundException(UsrErrorMessage.USER_NOT_FOUND);
        }
    }

    public void deleteWithControl(String username, String phoneNumber){

        UsrUser usrUser = checkIfUsernameAndPhoneNumberMatch(username,phoneNumber);

        super.delete(usrUser);

    }

    private UsrUser checkIfUsernameAndPhoneNumberMatch(String username, String phoneNumber){

        Optional<UsrUser> userOptional = checkIfUserExistsByUsername(username);

        UsrUser usrUser;
        if(userOptional.isPresent()){
             usrUser = userOptional.get();
        }else{
            throw new ItemNotFoundException(UsrErrorMessage.USER_NOT_FOUND);
        }

        if(!usrUser.getPhoneNumber().equals(phoneNumber)){
            throw new IllegalFieldException(username,phoneNumber,UsrErrorMessage.UNMATCHED_USERNAME_AND_PHONE_NUMBER);
        }

        return usrUser;

    }



}
