package com.softtech.softtechspringboot.dor.service.entityservice;

import com.softtech.softtechspringboot.dor.dao.DorDoorDao;
import com.softtech.softtechspringboot.dor.entity.DorDoor;
import com.softtech.softtechspringboot.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class DorDoorEntityService extends BaseEntityService<DorDoor, DorDoorDao> {

    public DorDoorEntityService(DorDoorDao dorDoorDao) {
        super(dorDoorDao);
        }

}
