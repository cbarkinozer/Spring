package com.softtech.graduationproject.app.usr.service.entityservice;

import com.softtech.graduationproject.app.gen.enums.GenStatusType;
import com.softtech.graduationproject.app.gen.service.BaseEntityService;
import com.softtech.graduationproject.app.usr.dao.UsrUserDao;
import com.softtech.graduationproject.app.usr.entity.UsrUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UsrUserEntityService extends BaseEntityService<UsrUser, UsrUserDao> {

    public UsrUserEntityService(UsrUserDao usrUserDao) {
        super(usrUserDao);
    }

    public List<UsrUser> findAllActiveUsers(){

        return getDao().findAllByStatusType(GenStatusType.ACTIVE);
    }

    public Optional<UsrUser> findUsersByUsername(String username){

        return getDao().findByUsername(username);

    }


}
