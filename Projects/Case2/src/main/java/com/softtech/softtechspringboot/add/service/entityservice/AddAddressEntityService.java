package com.softtech.softtechspringboot.add.service.entityservice;

import com.softtech.softtechspringboot.add.dao.AddAddressDao;
import com.softtech.softtechspringboot.add.entity.AddAddress;
import com.softtech.softtechspringboot.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class AddAddressEntityService extends BaseEntityService<AddAddress, AddAddressDao> {

    public AddAddressEntityService(AddAddressDao addAddressDao) {
        super(addAddressDao);
    }

}
