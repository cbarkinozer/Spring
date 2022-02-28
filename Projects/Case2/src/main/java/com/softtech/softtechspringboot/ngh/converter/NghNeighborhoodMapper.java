package com.softtech.softtechspringboot.ngh.converter;

import com.softtech.softtechspringboot.add.converter.AddAddressMapper;
import com.softtech.softtechspringboot.add.dto.AddAddressDto;
import com.softtech.softtechspringboot.add.dto.AddAddressSaveRequestDto;
import com.softtech.softtechspringboot.add.entity.AddAddress;
import com.softtech.softtechspringboot.ngh.dto.NghNeighborhoodDto;
import com.softtech.softtechspringboot.ngh.dto.NghNeighborhoodSaveRequestDto;
import com.softtech.softtechspringboot.ngh.entity.NghNeighborhood;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NghNeighborhoodMapper {
    NghNeighborhoodMapper INSTANCE = Mappers.getMapper(NghNeighborhoodMapper.class);

    NghNeighborhood convertToNghNeighborhood(NghNeighborhoodDto nghNeighborhoodDto);
    NghNeighborhood convertToNghNeighborhood(NghNeighborhoodSaveRequestDto nghNeighborhoodSaveRequestDto);
    List<NghNeighborhoodDto> convertToNeighborhoodDtoList(List<NghNeighborhood> nghNeighborhoodList);
    NghNeighborhoodDto convertToNghNeighborhoodDto(NghNeighborhood nghNeighborhood);
}
