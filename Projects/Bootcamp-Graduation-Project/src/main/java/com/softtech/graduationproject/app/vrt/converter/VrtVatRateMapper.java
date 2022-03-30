package com.softtech.graduationproject.app.vrt.converter;

import com.softtech.graduationproject.app.vrt.dto.VrtVatRateDto;
import com.softtech.graduationproject.app.vrt.dto.VrtVatRateSaveRequestDto;
import com.softtech.graduationproject.app.vrt.dto.VrtVatRateUpdateRequestDto;
import com.softtech.graduationproject.app.vrt.entity.VrtVatRate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VrtVatRateMapper {

    VrtVatRateMapper INSTANCE = Mappers.getMapper(VrtVatRateMapper.class);

    VrtVatRateDto convertToVrtVatRateDto(VrtVatRate vrtVatRate);

    List<VrtVatRateDto> convertToVrtVatRateDtoList(List<VrtVatRate> vrtVatRateList);

    VrtVatRate convertToVrtVatRate(VrtVatRateSaveRequestDto vrtVatRateSaveRequestDto);
    VrtVatRate convertToVrtVatRate(VrtVatRateUpdateRequestDto vrtVatRateUpdateRequestDto);

}
