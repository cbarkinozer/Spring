package com.softtech.graduationproject.app.vrt.service;

import com.softtech.graduationproject.app.gen.entity.BaseEntity;
import com.softtech.graduationproject.app.gen.exceptions.IllegalFieldException;
import com.softtech.graduationproject.app.gen.exceptions.ItemAlreadyExistsException;
import com.softtech.graduationproject.app.gen.exceptions.ItemNotFoundException;
import com.softtech.graduationproject.app.vrt.entity.VrtVatRate;
import com.softtech.graduationproject.app.vrt.enums.VrtErrorMessage;
import com.softtech.graduationproject.app.vrt.enums.VrtProductType;
import com.softtech.graduationproject.app.vrt.service.entityservice.VrtVatRateEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VrtVatRateValidationService {

    private final VrtVatRateEntityService vrtVatRateEntityService;

    public void controlIsVrtVatRateExist(Long id){

        boolean isExist = vrtVatRateEntityService.existsById(id);
        if (!isExist){
            throw new ItemNotFoundException(VrtErrorMessage.VAT_RATE_NOT_FOUND);
        }

    }

    public void controlAreFieldsNonNull(VrtVatRate vrtVatRate){

        boolean hasNullFields =
                        vrtVatRate.getProductType() == null ||
                        vrtVatRate.getVatRate() == null;

        if(hasNullFields){

            throw new IllegalFieldException(VrtErrorMessage.FIELDS_CANNOT_BE_NULL);
        }
    }

    public void controlIsVatRateNegative(VrtVatRate vrtVatRate) {

        if(vrtVatRate.getVatRate()<0){
            throw new IllegalFieldException(VrtErrorMessage.VAT_RATE_CANNOT_BE_NEGATIVE);
        }

    }

    public void controlIsProductTypeUnique(VrtVatRate vrtVatRate){

        VrtProductType vrtProductType = vrtVatRate.getProductType();

        Optional<VrtVatRate> optionalVrtVatRate = vrtVatRateEntityService.findVatRatesByProductType(vrtProductType);

        VrtVatRate vrtVatRateReturned;
        if(optionalVrtVatRate.isPresent()){

            vrtVatRateReturned = optionalVrtVatRate.get();
            boolean didMatchedItself = didMatchedItself(vrtVatRateReturned, vrtVatRate);

            if(!didMatchedItself){
                throw new ItemAlreadyExistsException(VrtErrorMessage.VAT_RATE_ALREADY_EXIST);
            }

        }
    }

    private Boolean didMatchedItself(VrtVatRate vrtVatRateReturned, VrtVatRate vrtVatRate){

        return vrtVatRateReturned.getId().equals(vrtVatRate.getId());
    }


}
