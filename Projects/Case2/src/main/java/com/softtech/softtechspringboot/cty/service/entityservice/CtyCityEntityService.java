package com.softtech.softtechspringboot.cty.service.entityservice;


import com.softtech.softtechspringboot.cty.dao.CtyCityDao;
import com.softtech.softtechspringboot.cty.entity.CtyCity;
import com.softtech.softtechspringboot.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;


@Service
public class CtyCityEntityService extends BaseEntityService<CtyCity, CtyCityDao> {
    public CtyCityEntityService(CtyCityDao ctyCityDao) {
        super(ctyCityDao);
    }


}
