package com.softtech.softtechspringboot.cnt.service.entityservice;

import com.softtech.softtechspringboot.cnt.dao.CntCountryDao;
import com.softtech.softtechspringboot.cnt.entity.CntCountry;
import com.softtech.softtechspringboot.cnt.enums.CntErrorMessage;
import com.softtech.softtechspringboot.gen.exceptions.ItemNotFoundException;
import com.softtech.softtechspringboot.gen.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CntCountryEntityService extends BaseEntityService<CntCountry, CntCountryDao> {
    private final CntCountryDao cntCountryDao;
    @Autowired
    public CntCountryEntityService(CntCountryDao cntCountryDao) {
        super(cntCountryDao);
        this.cntCountryDao = cntCountryDao;
    }

    public CntCountry getByCountryCodeWithControl(String countryCode) {
        CntCountry cntCountry= cntCountryDao.getCountryByCountryCode(countryCode);
        if (cntCountry==null){
            throw new ItemNotFoundException(CntErrorMessage.COUNTRY_ERROR_MESSAGE);
        }
        return cntCountry;
    }

}
