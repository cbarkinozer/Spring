package com.softtech.softtechspringboot.dst.converter;

import com.softtech.softtechspringboot.dst.dto.DstDistrictDto;
import com.softtech.softtechspringboot.dst.dto.DstDistrictSaveRequestDto;
import com.softtech.softtechspringboot.dst.entity.DstDistrict;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DstDistrictMapper {
    DstDistrictMapper INSTANCE = Mappers.getMapper(DstDistrictMapper.class);

    DstDistrict convertToDstDistrict(DstDistrictDto dstDistrictDto);
    DstDistrict convertToDstDistrict(DstDistrictSaveRequestDto DstDistrictSaveRequestDto);
    List<DstDistrictDto> convertToDstDistrictDtoList(List<DstDistrict> DstDistrictList);
    DstDistrictDto convertToDstDistrictDto(DstDistrict dstDistrict);
}
