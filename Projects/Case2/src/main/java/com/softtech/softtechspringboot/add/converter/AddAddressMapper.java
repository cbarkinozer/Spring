package com.softtech.softtechspringboot.add.converter;

import com.softtech.softtechspringboot.add.dto.AddAddressDto;
import com.softtech.softtechspringboot.add.dto.AddAddressSaveRequestDto;
import com.softtech.softtechspringboot.add.entity.AddAddress;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddAddressMapper {
    AddAddressMapper INSTANCE = Mappers.getMapper(AddAddressMapper.class);

    AddAddress convertToAddAddress(AddAddressDto addAddressDto);
    AddAddress convertToAddAddress(AddAddressSaveRequestDto addAddressSaveRequestDto);
    List<AddAddressDto> convertToAddAddressDtoList(List<AddAddress> addAddressList);
    AddAddressDto convertToAddAddressDto(AddAddress addAddress);
}
