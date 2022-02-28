package com.softtech.softtechspringboot.dst.dao;

import com.softtech.softtechspringboot.dst.entity.DstDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DstDistrictDao extends JpaRepository<DstDistrict,Long> {
    DstDistrict getDistrictByCityCode(Long cityId);
}
