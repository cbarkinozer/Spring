package com.softtech.softtechspringboot.cty.service;

import com.softtech.softtechspringboot.cty.converter.CtyCityMapper;
import com.softtech.softtechspringboot.cty.dto.CtyCityDto;
import com.softtech.softtechspringboot.cty.dto.CtyCitySaveRequestDto;
import com.softtech.softtechspringboot.cty.entity.CtyCity;
import com.softtech.softtechspringboot.cty.enums.CtyErrorMessage;
import com.softtech.softtechspringboot.cty.service.entityservice.CtyCityEntityService;
import com.softtech.softtechspringboot.gen.exceptions.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CtyCityService {
    private final CtyCityEntityService ctyCityEntityService;

    public CtyCityDto save(CtyCitySaveRequestDto ctyCitySaveRequestDto) {
        CtyCity ctyCity = CtyCityMapper.INSTANCE.convertToCtyCity(ctyCitySaveRequestDto);
        ctyCity = ctyCityEntityService.save(ctyCity);
        return CtyCityMapper.INSTANCE.convertToCtyCityDto(ctyCity);
    }

    public CtyCityDto findByCityCode(Long cityCode) {
        CtyCity ctyCity = ctyCityEntityService.getByIdWithControl(cityCode);
        return CtyCityMapper.INSTANCE.convertToCtyCityDto(ctyCity);
    }

    public CtyCity getById(long id){
        return ctyCityEntityService.findById(id).orElseThrow(() -> new ItemNotFoundException(CtyErrorMessage.CITY_ERROR_MESSAGE));
    }

}
