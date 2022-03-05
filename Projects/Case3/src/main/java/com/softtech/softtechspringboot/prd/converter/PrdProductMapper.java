package com.softtech.softtechspringboot.prd.converter;


import com.softtech.softtechspringboot.prd.dto.PrdProductDto;
import com.softtech.softtechspringboot.prd.entity.PrdProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrdProductMapper {
    PrdProductMapper INSTANCE = Mappers.getMapper(PrdProductMapper.class);

    PrdProduct convertToPrdProduct(PrdProductDto prdProductDto);
    List<PrdProductDto> convertToPrdProductDtoList(List<PrdProduct> prdProductList);
    PrdProductDto convertToPrdProductDto(PrdProduct prdProduct);
}
