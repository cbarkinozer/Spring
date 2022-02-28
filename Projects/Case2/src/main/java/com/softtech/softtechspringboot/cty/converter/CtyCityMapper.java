package com.softtech.softtechspringboot.cty.converter;

import com.softtech.softtechspringboot.cty.dto.CtyCityDto;
import com.softtech.softtechspringboot.cty.dto.CtyCitySaveRequestDto;
import com.softtech.softtechspringboot.cty.entity.CtyCity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CtyCityMapper {
    CtyCityMapper INSTANCE = Mappers.getMapper(CtyCityMapper.class);

    CtyCity convertToCtyCity(CtyCityDto ctyCityDto);
    CtyCity convertToCtyCity(CtyCitySaveRequestDto ctyCitySaveRequestDto);
    List<CtyCityDto> convertToCtyCityDtoList(List<CtyCity> ctyCityList);
    CtyCityDto convertToCtyCityDto(CtyCity ctyCity);
}
