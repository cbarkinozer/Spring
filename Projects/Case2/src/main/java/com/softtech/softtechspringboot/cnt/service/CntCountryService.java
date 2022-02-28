package com.softtech.softtechspringboot.cnt.service;

import com.softtech.softtechspringboot.cnt.converter.CntCountryMapper;
import com.softtech.softtechspringboot.cnt.dto.CntCountryDto;
import com.softtech.softtechspringboot.cnt.dto.CntCountrySaveRequestDto;
import com.softtech.softtechspringboot.cnt.entity.CntCountry;
import com.softtech.softtechspringboot.cnt.service.entityservice.CntCountryEntityService;
import com.softtech.softtechspringboot.cty.converter.CtyCityMapper;
import com.softtech.softtechspringboot.cty.dto.CtyCityDto;
import com.softtech.softtechspringboot.cty.entity.CtyCity;
import com.softtech.softtechspringboot.cty.service.entityservice.CtyCityEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CntCountryService {
    private final CntCountryEntityService cntCountryEntityService;
    public CntCountryDto save(CntCountrySaveRequestDto cntCountrySaveRequestDto) {
        CntCountry cntCountry = CntCountryMapper.INSTANCE.convertToCntCountry(cntCountrySaveRequestDto);
        cntCountry = cntCountryEntityService.save(cntCountry);
        return CntCountryMapper.INSTANCE.convertToCntCountryDto(cntCountry);
    }

    public CntCountryDto findByCountryCode(String countryCode) {
        CntCountry cntCountry = cntCountryEntityService.getByCountryCodeWithControl(countryCode);
        return CntCountryMapper.INSTANCE.convertToCntCountryDto(cntCountry);
    }


}
