package com.softtech.softtechspringboot.str.service.entityservice;


import com.softtech.softtechspringboot.gen.service.BaseEntityService;
import com.softtech.softtechspringboot.str.dao.StrStreetDao;
import com.softtech.softtechspringboot.str.entity.StrStreet;
import org.springframework.stereotype.Service;

@Service
public class StrStreetEntityService extends BaseEntityService<StrStreet, StrStreetDao> {
    public StrStreetEntityService(StrStreetDao strStreetDao) {
        super(strStreetDao);
    }
}
