package com.softtech.softtechspringboot.dst.service;

import com.softtech.softtechspringboot.dst.converter.DstDistrictMapper;
import com.softtech.softtechspringboot.dst.dto.DstDistrictDto;
import com.softtech.softtechspringboot.dst.dto.DstDistrictSaveRequestDto;
import com.softtech.softtechspringboot.dst.entity.DstDistrict;
import com.softtech.softtechspringboot.dst.service.entityservice.DstDistrictEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DstDistrictService {
    private final DstDistrictEntityService dstDistrictEntityService;

    public DstDistrictDto save(DstDistrictSaveRequestDto dstDistrictSaveRequestDto) {
        DstDistrict dstDistrict = DstDistrictMapper.INSTANCE.convertToDstDistrict(dstDistrictSaveRequestDto);
        dstDistrict = dstDistrictEntityService.save(dstDistrict);
        return DstDistrictMapper.INSTANCE.convertToDstDistrictDto(dstDistrict);
    }

    public DstDistrictDto findByCityId(Long cityId) {
        DstDistrict dstDistrict = dstDistrictEntityService.getByCityCodeWithControl(cityId);
        return DstDistrictMapper.INSTANCE.convertToDstDistrictDto(dstDistrict);
    }
}
