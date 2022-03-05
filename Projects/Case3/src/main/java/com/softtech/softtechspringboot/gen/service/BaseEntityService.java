package com.softtech.softtechspringboot.gen.service;

import com.softtech.softtechspringboot.gen.entity.BaseAdditionalFields;
import com.softtech.softtechspringboot.gen.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity,D extends JpaRepository<E,Long>> {

    private final D dao;

    public List<E> findAll(){
        return dao.findAll();
    }

    public E save(E entity){
        setAdditionalFields(entity);
        return dao.save(entity);
    }

    private void setAdditionalFields(E entity) {

        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        Long currentCustomerId = getCurrentCustomerId();

        if (baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }

        if (entity.getId() == null){
            baseAdditionalFields.setCreateDate(new Date());
            baseAdditionalFields.setCreatedBy(currentCustomerId);
        }

        baseAdditionalFields.setUpdateDate(new Date());
        baseAdditionalFields.setUpdatedBy(currentCustomerId);
    }

    public void delete(E entity){
        dao.delete(entity);
    }


    //TODO: control after jwt
    private Long getCurrentCustomerId() {
        Long currentCustomer = null;
        return currentCustomer;
    }

}
