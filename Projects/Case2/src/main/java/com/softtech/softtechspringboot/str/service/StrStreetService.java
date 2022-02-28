package com.softtech.softtechspringboot.str.service;


import com.softtech.softtechspringboot.gen.exceptions.ItemNotFoundException;
import com.softtech.softtechspringboot.str.converter.StrStreetMapper;
import com.softtech.softtechspringboot.str.dto.StrStreetDto;
import com.softtech.softtechspringboot.str.dto.StrStreetSaveRequestDto;
import com.softtech.softtechspringboot.str.entity.StrStreet;
import com.softtech.softtechspringboot.str.enums.StrErrorMessage;
import com.softtech.softtechspringboot.str.service.entityservice.StrStreetEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StrStreetService {
    private final StrStreetEntityService strStreetEntityService;


    public StrStreetDto save(StrStreetSaveRequestDto strStreetSaveRequestDto) {
        StrStreet strStreet = StrStreetMapper.INSTANCE.convertToStrStreet(strStreetSaveRequestDto);
        strStreet = strStreetEntityService.save(strStreet);
        return StrStreetMapper.INSTANCE.convertToStrStreetDto(strStreet);
    }

    public StrStreetDto update(StrStreetDto strStreetDto) {
        controlIsStreetExist(strStreetDto);
        StrStreet updateStreet = StrStreetMapper.INSTANCE.convertToStrStreet(strStreetDto);
        updateStreet = strStreetEntityService.save(updateStreet);
        return StrStreetMapper.INSTANCE.convertToStrStreetDto(updateStreet);
    }
    private void controlIsStreetExist(StrStreetDto strStreetDto) {
        Long id = strStreetDto.getId();
        boolean isExist = strStreetEntityService.existsById(id);
        if (!isExist){
            throw new ItemNotFoundException(StrErrorMessage.STREET_ERROR_MESSAGE);
        }
    }
}
