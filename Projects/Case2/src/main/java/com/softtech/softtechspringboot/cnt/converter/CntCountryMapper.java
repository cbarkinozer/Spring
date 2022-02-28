package com.softtech.softtechspringboot.cnt.converter;

import com.softtech.softtechspringboot.cnt.dto.CntCountryDto;
import com.softtech.softtechspringboot.cnt.dto.CntCountrySaveRequestDto;
import com.softtech.softtechspringboot.cnt.entity.CntCountry;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CntCountryMapper {
    CntCountryMapper INSTANCE = Mappers.getMapper(CntCountryMapper.class);

    CntCountry convertToCntCountry(CntCountryDto cntCountryDto);
    CntCountry convertToCntCountry(CntCountrySaveRequestDto cntCountrySaveRequestDto);
    List<CntCountryDto> convertToCntCountryDtoList(List<CntCountry> cntCountryList);
    CntCountryDto convertToCntCountryDto(CntCountry cntCountry);
}
