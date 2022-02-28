package com.softtech.softtechspringboot.ngh.service;

import com.softtech.softtechspringboot.add.enums.AddErrorMessage;
import com.softtech.softtechspringboot.gen.exceptions.ItemNotFoundException;
import com.softtech.softtechspringboot.ngh.converter.NghNeighborhoodMapper;
import com.softtech.softtechspringboot.ngh.dto.NghNeighborhoodDto;
import com.softtech.softtechspringboot.ngh.dto.NghNeighborhoodSaveRequestDto;
import com.softtech.softtechspringboot.ngh.entity.NghNeighborhood;
import com.softtech.softtechspringboot.ngh.service.entityservice.NghNeighborhoodEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NghNeighborhoodService {
    private final NghNeighborhoodEntityService nghNeighborhoodEntityService;

    public NghNeighborhoodDto save(NghNeighborhoodSaveRequestDto nghNeighborhoodSaveRequestDto) {
        NghNeighborhood nghNeighborhood = NghNeighborhoodMapper.INSTANCE.convertToNghNeighborhood(nghNeighborhoodSaveRequestDto);
        nghNeighborhood = nghNeighborhoodEntityService.save(nghNeighborhood);
        return NghNeighborhoodMapper.INSTANCE.convertToNghNeighborhoodDto(nghNeighborhood);
    }

    public NghNeighborhoodDto update(NghNeighborhoodDto nghNeighborhoodDto) {
        controlIsNeighborhoodExist(nghNeighborhoodDto);
        NghNeighborhood updateNeighborhood = NghNeighborhoodMapper.INSTANCE.convertToNghNeighborhood(nghNeighborhoodDto);
        updateNeighborhood = nghNeighborhoodEntityService.save(updateNeighborhood);
        return NghNeighborhoodMapper.INSTANCE.convertToNghNeighborhoodDto(updateNeighborhood);
    }

    private void controlIsNeighborhoodExist(NghNeighborhoodDto nghNeighborhoodDto) {
        Long id = nghNeighborhoodDto.getId();
        boolean isExist = nghNeighborhoodEntityService.existsById(id);
        if (!isExist){
            throw new ItemNotFoundException(AddErrorMessage.ADDRESS_ERROR_MESSAGE);
        }
    }

    public NghNeighborhoodDto findByDistrictId(Long id) {
        NghNeighborhoodDto nghNeighborhoodDto = nghNeighborhoodEntityService.getByDistrictIdWithControl(id);
        return nghNeighborhoodDto ;
    }


}
