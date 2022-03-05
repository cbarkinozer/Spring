package com.softtech.softtechspringboot.prd.service;


import com.softtech.softtechspringboot.gen.exceptions.ItemNotFoundException;
import com.softtech.softtechspringboot.prd.converter.PrdProductMapper;
import com.softtech.softtechspringboot.prd.dto.PrdProductDto;
import com.softtech.softtechspringboot.prd.entity.PrdProduct;
import com.softtech.softtechspringboot.prd.enums.PrdErrorMessage;
import com.softtech.softtechspringboot.prd.service.entityservice.PrdProductEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrdProductService {

    private final PrdProductEntityService prdProductEntityService;

    public List<PrdProductDto> findAll(){
        List<PrdProduct> prdProductList = prdProductEntityService.findAll();
        return PrdProductMapper.INSTANCE.convertToPrdProductDtoList(prdProductList);
    }

    public PrdProductDto findById(Long id){
        PrdProduct prdProduct = prdProductEntityService.findById(id);
        return PrdProductMapper.INSTANCE.convertToPrdProductDto(prdProduct);
    }

    public PrdProductDto save(PrdProductDto prdProductDto){
        PrdProduct prdProduct = PrdProductMapper.INSTANCE.convertToPrdProduct(prdProductDto);
        prdProduct = prdProductEntityService.save(prdProduct);
        return PrdProductMapper.INSTANCE.convertToPrdProductDto(prdProduct);
    }

    public PrdProductDto updatePrice(Long id, BigDecimal price) {
        PrdProduct prdProduct = prdProductEntityService.updatePrice(id,price);
        return PrdProductMapper.INSTANCE.convertToPrdProductDto(prdProduct);
    }

    public void delete(Long id){
        PrdProduct prdProduct = prdProductEntityService.findById(id);
        prdProductEntityService.delete(prdProduct);
    }

}
