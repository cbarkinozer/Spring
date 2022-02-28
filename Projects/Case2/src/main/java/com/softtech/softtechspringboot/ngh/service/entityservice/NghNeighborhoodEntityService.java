package com.softtech.softtechspringboot.ngh.service.entityservice;

import com.softtech.softtechspringboot.cnt.enums.CntErrorMessage;
import com.softtech.softtechspringboot.dst.entity.DstDistrict;
import com.softtech.softtechspringboot.gen.exceptions.ItemNotFoundException;
import com.softtech.softtechspringboot.gen.service.BaseEntityService;
import com.softtech.softtechspringboot.ngh.converter.NghNeighborhoodMapper;
import com.softtech.softtechspringboot.ngh.dao.NghNeighborhoodDao;
import com.softtech.softtechspringboot.ngh.dto.NghNeighborhoodDto;
import com.softtech.softtechspringboot.ngh.entity.NghNeighborhood;
import com.softtech.softtechspringboot.ngh.enums.NghErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NghNeighborhoodEntityService extends BaseEntityService<NghNeighborhood, NghNeighborhoodDao> {
    private final NghNeighborhoodDao nghNeighborhoodDao;
    @Autowired
    public NghNeighborhoodEntityService(NghNeighborhoodDao nghNeighborhoodDao) {
        super(nghNeighborhoodDao);
        this.nghNeighborhoodDao=nghNeighborhoodDao;
    }

    public NghNeighborhoodDto getByDistrictIdWithControl(Long districtId) {
        NghNeighborhood nghNeighborhood = nghNeighborhoodDao.getNeighborhoodByDistrictId(districtId);
        return NghNeighborhoodMapper.INSTANCE.convertToNghNeighborhoodDto(nghNeighborhood);
    }
}
