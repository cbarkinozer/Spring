package com.softtech.graduationproject.app.prd.service;

import com.softtech.graduationproject.app.gen.exceptions.ItemNotFoundException;
import com.softtech.graduationproject.app.prd.converter.PrdProductMapper;
import com.softtech.graduationproject.app.prd.dto.*;
import com.softtech.graduationproject.app.prd.entity.PrdProduct;
import com.softtech.graduationproject.app.prd.service.entityservice.PrdProductEntityService;
import com.softtech.graduationproject.app.vrt.entity.VrtVatRate;
import com.softtech.graduationproject.app.vrt.enums.VrtErrorMessage;
import com.softtech.graduationproject.app.vrt.enums.VrtProductType;
import com.softtech.graduationproject.app.vrt.service.entityservice.VrtVatRateEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PrdProductService {

    private final PrdProductEntityService prdProductEntityService;
    private final VrtVatRateEntityService vrtVatRateEntityService;

    private final PrdProductValidationService prdProductValidationService;
    private final PrdProductUtilityService prdProductUtilityService;


    public List<PrdProductDto> findAllProducts() {

        List<PrdProduct> prdProductList = prdProductEntityService.findAll();

        List<PrdProductDto> prdProductDtoList = PrdProductMapper.INSTANCE.convertToPrdProductDtoList(prdProductList);

        return prdProductDtoList;
    }


    public PrdProductDto findProductById(Long id) {

        PrdProduct prdProduct = prdProductEntityService.getByIdWithControl(id);

        PrdProductDto prdProductDto = PrdProductMapper.INSTANCE.convertToPrdProductDto(prdProduct);

        return prdProductDto;
    }


    public List<PrdProductDto> findProductsByPriceInterval(BigDecimal min, BigDecimal max) {

        prdProductValidationService.controlIsParameterMinIsLargerThanMax(min,max);

        List<PrdProduct> prdProductList = prdProductEntityService.findProductsByPriceInterval(min,max);

        List<PrdProductDto> prdProductDtoList = PrdProductMapper.INSTANCE.convertToPrdProductDtoList(prdProductList);

        return prdProductDtoList;

    }


    public List<PrdProductDto> findProductsByProductType(VrtProductType vrtProductType) {

        prdProductValidationService.controlIsParameterNull(vrtProductType);

         VrtVatRate vrtVatRate = vrtVatRateEntityService.findVatRatesByProductType(vrtProductType)
                 .orElseThrow(()-> new ItemNotFoundException(VrtErrorMessage.VAT_RATE_NOT_FOUND));

         Long vrtVatRateId = vrtVatRate.getId();

         List<PrdProduct> prdProductList = prdProductEntityService.findProductsByVatRateId(vrtVatRateId);

         List<PrdProductDto> prdProductDtoList = PrdProductMapper.INSTANCE.convertToPrdProductDtoList(prdProductList);

         return prdProductDtoList;

    }


    public List<PrdProductAnalysisRequestDto> getProductAnalysis() {

        List<PrdProductAnalysisRequestDto> prdProductAnalysisRequestDtoList = prdProductEntityService.getProductAnalysis();

        return prdProductAnalysisRequestDtoList;
    }


    public PrdProductDto saveProduct(PrdProductSaveRequestDto prdProductSaveRequestDto) {

        prdProductValidationService.controlIsParameterNull(prdProductSaveRequestDto);

        PrdProduct prdProduct = PrdProductMapper.INSTANCE.convertToPrdProduct(prdProductSaveRequestDto);

        prdProductValidationService.controlAreFieldsNonNull(prdProduct);

        BigDecimal price = prdProductUtilityService.calculatePriceWithControl(prdProduct);
        prdProduct.setPrice(price);

        prdProduct = prdProductEntityService.save(prdProduct);

        PrdProductDto prdProductDto = PrdProductMapper.INSTANCE.convertToPrdProductDto(prdProduct);

        return prdProductDto;
    }


    public PrdProductDto updateProduct(PrdProductUpdateRequestDto prdProductUpdateRequestDto) {

        prdProductValidationService.controlIsParameterNull(prdProductUpdateRequestDto);

        Long id = prdProductUpdateRequestDto.getId();
        prdProductValidationService.controlIsPrdProductExist(id);

        PrdProduct prdProduct = PrdProductMapper.INSTANCE.convertToPrdProduct(prdProductUpdateRequestDto);

        prdProductValidationService.controlAreFieldsNonNull(prdProduct);

        BigDecimal price = prdProductUtilityService.calculatePriceWithControl(prdProduct);
        prdProduct.setPrice(price);

        prdProduct = prdProductEntityService.save(prdProduct);

        PrdProductDto prdProductDto = PrdProductMapper.INSTANCE.convertToPrdProductDto(prdProduct);

        return prdProductDto;
    }


    public void batchProductUpdate(Long vrtVatRateId){

        prdProductValidationService.controlIsParameterNull(vrtVatRateId);

        List<PrdProduct> prdProductList = prdProductEntityService.findProductsByVatRateId(vrtVatRateId);

        prdProductValidationService.controlIsListNull(prdProductList);

        if(!prdProductList.isEmpty()){

            for(PrdProduct prdProduct : prdProductList){

                BigDecimal price = prdProductUtilityService.calculatePriceWithControl(prdProduct);
                prdProduct.setPrice(price);

                prdProductEntityService.save(prdProduct);

            }

        }

    }


    public void deleteProduct(Long id) {

        prdProductValidationService.controlIsParameterNull(id);

        PrdProduct prdProduct = prdProductEntityService.getByIdWithControl(id);

        prdProductEntityService.delete(prdProduct);

    }



}
