package com.softtech.graduationproject.app.prd.converter;

import com.softtech.graduationproject.app.prd.dto.PrdProductDto;
import com.softtech.graduationproject.app.prd.entity.PrdProduct;
import com.softtech.graduationproject.app.prd.dto.PrdProductSaveRequestDto;
import com.softtech.graduationproject.app.prd.dto.PrdProductUpdateRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrdProductMapper {

    PrdProductMapper INSTANCE = Mappers.getMapper(PrdProductMapper.class);

    PrdProduct convertToPrdProduct(PrdProductSaveRequestDto prdProductSaveRequestDto);

    PrdProduct convertToPrdProduct(PrdProductUpdateRequestDto prdProductUpdateRequestDto);

    List<PrdProductDto> convertToPrdProductDtoList(List<PrdProduct> prdProductList);

    PrdProductDto convertToPrdProductDto(PrdProduct prdProduct);
}
