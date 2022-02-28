package com.softtech.softtechspringboot.str.converter;


import com.softtech.softtechspringboot.str.dto.StrStreetDto;
import com.softtech.softtechspringboot.str.dto.StrStreetSaveRequestDto;
import com.softtech.softtechspringboot.str.entity.StrStreet;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StrStreetMapper {
    StrStreetMapper INSTANCE = Mappers.getMapper(StrStreetMapper.class);

    StrStreet convertToStrStreet(StrStreetDto strStreetDto);
    StrStreet convertToStrStreet(StrStreetSaveRequestDto strStreetSaveRequestDto);
    List<StrStreetDto> convertToStrStreetDtoList(List<StrStreet> strStreetList);
    StrStreetDto convertToStrStreetDto(StrStreet strStreet);
}
