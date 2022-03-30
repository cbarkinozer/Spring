package com.softtech.graduationproject.app.prd.service;

import com.softtech.graduationproject.app.gen.exceptions.IllegalFieldException;
import com.softtech.graduationproject.app.gen.exceptions.ItemNotFoundException;
import com.softtech.graduationproject.app.prd.dto.PrdProductSaveRequestDto;
import com.softtech.graduationproject.app.prd.dto.PrdProductUpdateRequestDto;
import com.softtech.graduationproject.app.prd.entity.PrdProduct;
import com.softtech.graduationproject.app.prd.enums.PrdErrorMessage;
import com.softtech.graduationproject.app.prd.service.entityservice.PrdProductEntityService;
import com.softtech.graduationproject.app.vrt.enums.VrtProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PrdProductValidationService {

    private final PrdProductEntityService prdProductEntityService;

    public void controlIsPrdProductExist(Long id){

        boolean isExist = prdProductEntityService.existsById(id);
        if (!isExist){
            throw new ItemNotFoundException(PrdErrorMessage.PRODUCT_NOT_FOUND);
        }

    }

    public void controlAreFieldsNonNull(PrdProduct prdProduct) {

        boolean hasNullField = prdProduct.getUsrUserId() == null ||
                          prdProduct.getVrtVatRateId() == null   ||
                          prdProduct.getName().isBlank()         ||
                          prdProduct.getVatFreePrice() == null;

        if(hasNullField){

            throw new IllegalFieldException(PrdErrorMessage.FIELD_CANNOT_BE_NULL);
        }
    }


    public void controlIsPricePositive(BigDecimal price){

        if(price.compareTo(BigDecimal.ZERO) < 1 ){

            throw new IllegalFieldException(PrdErrorMessage.PRICE_MUST_BE_POSITIVE);
        }
    }

    public void controlIsPriceNull(BigDecimal price) {

        if(price == null){
            throw new IllegalFieldException(PrdErrorMessage.PRICE_CANNOT_BE_NULL);
        }

    }


    public void controlIsParameterMinIsLargerThanMax(BigDecimal min, BigDecimal max) {
        if(min.compareTo(max) > 0){
            throw new IllegalFieldException(PrdErrorMessage.PARAMETER_MIN_CANNOT_BE_LARGER_THAN_MAX);
        }
    }

    public void controlIsParameterNull(PrdProductSaveRequestDto prdProductSaveRequestDto) {

        if(prdProductSaveRequestDto == null){
            throw new IllegalFieldException(PrdErrorMessage.FIELD_CANNOT_BE_NULL);
        }

    }

    public void controlIsParameterNull(PrdProductUpdateRequestDto prdProductUpdateRequestDto) {

        if(prdProductUpdateRequestDto == null){
            throw new IllegalFieldException(PrdErrorMessage.FIELD_CANNOT_BE_NULL);
        }

    }

    public void controlIsParameterNull(Long id) {

        if(id == null){
            throw new IllegalFieldException(PrdErrorMessage.ID_VALUES_CANNOT_BE_NULL);
        }

    }

    public void controlIsParameterNull(VrtProductType vrtProductType) {

        if(vrtProductType == null){
            throw new IllegalFieldException(PrdErrorMessage.PRODUCT_TYPE_CANNOT_BE_NULL);
        }

    }

    public void controlIsListNull(List<?> entityList) {

        if(entityList == null){
            throw new ItemNotFoundException(PrdErrorMessage.PRODUCT_NOT_FOUND);
        }

    }


}
