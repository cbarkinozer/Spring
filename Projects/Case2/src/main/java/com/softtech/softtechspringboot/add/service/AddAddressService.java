package com.softtech.softtechspringboot.add.service;

import com.softtech.softtechspringboot.add.converter.AddAddressMapper;
import com.softtech.softtechspringboot.add.dto.AddAddressDto;
import com.softtech.softtechspringboot.add.dto.AddAddressSaveRequestDto;
import com.softtech.softtechspringboot.add.entity.AddAddress;
import com.softtech.softtechspringboot.add.enums.AddErrorMessage;
import com.softtech.softtechspringboot.add.service.entityservice.AddAddressEntityService;
import com.softtech.softtechspringboot.cnt.converter.CntCountryMapper;
import com.softtech.softtechspringboot.cnt.entity.CntCountry;
import com.softtech.softtechspringboot.gen.exceptions.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddAddressService {

    private final AddAddressEntityService addAddressEntityService;


    public List<AddAddressDto> findAll() {
        List<AddAddress> addAddressList = addAddressEntityService.findAll();
        return AddAddressMapper.INSTANCE.convertToAddAddressDtoList(addAddressList);
    }


    public AddAddressDto findById(Long id) {
        AddAddress addAddress = addAddressEntityService.getByIdWithControl(id);
        return AddAddressMapper.INSTANCE.convertToAddAddressDto(addAddress);

    }


    public AddAddressDto save(AddAddressSaveRequestDto addAddressSaveRequestDto) {
       AddAddress addAddress = AddAddressMapper.INSTANCE.convertToAddAddress(addAddressSaveRequestDto);
       addAddress = addAddressEntityService.save(addAddress);
       return AddAddressMapper.INSTANCE.convertToAddAddressDto(addAddress);
    }

    public void delete(Long id) {
       AddAddress addAddress = addAddressEntityService.getByIdWithControl(id);
       addAddressEntityService.delete(addAddress);
    }

    public AddAddressDto update(AddAddressDto addAddressDto) {
        controlIsAddressExist(addAddressDto);
        AddAddress updateAddress = AddAddressMapper.INSTANCE.convertToAddAddress(addAddressDto);
        updateAddress = addAddressEntityService.save(updateAddress);
        return AddAddressMapper.INSTANCE.convertToAddAddressDto(updateAddress);
    }
    private void controlIsAddressExist(AddAddressDto addAddressDto) {
        Long id = addAddressDto.getId();
        boolean isExist = addAddressEntityService.existsById(id);
        if (!isExist){
            throw new ItemNotFoundException(AddErrorMessage.ADDRESS_ERROR_MESSAGE);
        }
    }
}
