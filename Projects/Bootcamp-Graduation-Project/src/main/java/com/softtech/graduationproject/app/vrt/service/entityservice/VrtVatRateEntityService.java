package com.softtech.graduationproject.app.vrt.service.entityservice;

import com.softtech.graduationproject.app.gen.service.BaseEntityService;
import com.softtech.graduationproject.app.vrt.dao.VrtVatRateDao;
import com.softtech.graduationproject.app.vrt.entity.VrtVatRate;
import com.softtech.graduationproject.app.vrt.enums.VrtProductType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class VrtVatRateEntityService extends BaseEntityService<VrtVatRate, VrtVatRateDao> {

    public VrtVatRateEntityService(VrtVatRateDao vrtVatRateDao){

        super(vrtVatRateDao);
    }

    public Optional<VrtVatRate> findVatRatesByProductType(VrtProductType vrtProductType){

        Optional<VrtVatRate> vrtVatRateOptional = getDao().findByProductType(vrtProductType);

        return vrtVatRateOptional;
    }

    public Optional<VrtVatRate> findVrtVatRateById(Long id){

        Optional<VrtVatRate> vrtVatRateOptional = getDao().findVrtVatRateById(id);

        return vrtVatRateOptional;
    }

}
