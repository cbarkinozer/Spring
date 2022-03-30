package com.softtech.graduationproject.app.prd.service;

import com.softtech.graduationproject.app.gen.exceptions.ItemNotFoundException;
import com.softtech.graduationproject.app.prd.entity.PrdProduct;
import com.softtech.graduationproject.app.prd.service.entityservice.PrdProductEntityService;
import com.softtech.graduationproject.app.vrt.entity.VrtVatRate;
import com.softtech.graduationproject.app.vrt.enums.VrtErrorMessage;
import com.softtech.graduationproject.app.vrt.service.entityservice.VrtVatRateEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class PrdProductUtilityService {

    private final PrdProductValidationService prdProductValidationService;

    private final VrtVatRateEntityService vrtVatRateEntityService;

    public BigDecimal calculatePriceWithControl(PrdProduct prdProduct){

        Long vrtVatRateId = prdProduct.getVrtVatRateId();

        VrtVatRate vrtVatRate = vrtVatRateEntityService.findVrtVatRateById(vrtVatRateId)
                .orElseThrow(()-> new ItemNotFoundException(VrtErrorMessage.VAT_RATE_NOT_FOUND));

        Integer vatRateInt = vrtVatRate.getVatRate();

        Double vatRate = Double.valueOf(vatRateInt);

        BigDecimal vatFreePrice = prdProduct.getVatFreePrice();

        prdProductValidationService.controlIsPriceNull(vatFreePrice);

        BigDecimal price = vatFreePrice.add(vatFreePrice.multiply(BigDecimal.valueOf(vatRate/100)));

        prdProductValidationService.controlIsPricePositive(price);

        return price;
    }
}
