package com.softtech.softtechspringboot.dst.service.entityservice;


import com.softtech.softtechspringboot.dst.dao.DstDistrictDao;
import com.softtech.softtechspringboot.dst.entity.DstDistrict;
import com.softtech.softtechspringboot.dst.enums.DstErrorMessage;
import com.softtech.softtechspringboot.gen.exceptions.ItemNotFoundException;
import com.softtech.softtechspringboot.gen.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DstDistrictEntityService extends BaseEntityService<DstDistrict, DstDistrictDao> {
    private final DstDistrictDao dstDistrictDao;
    @Autowired
    public DstDistrictEntityService(DstDistrictDao dstDistrictDao) {
        super(dstDistrictDao);
        this.dstDistrictDao=dstDistrictDao;
    }

    public DstDistrict getByCityCodeWithControl(Long cityId) {
        DstDistrict dstDistrict= dstDistrictDao.getDistrictByCityCode(cityId);
        if (dstDistrict==null){
            throw new ItemNotFoundException(DstErrorMessage.DISTRICT_ERROR_MESSAGE);
        }
        return dstDistrict;
    }

}
