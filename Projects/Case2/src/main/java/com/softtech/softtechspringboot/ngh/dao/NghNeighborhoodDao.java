package com.softtech.softtechspringboot.ngh.dao;

import com.softtech.softtechspringboot.ngh.entity.NghNeighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NghNeighborhoodDao extends JpaRepository<NghNeighborhood,Long> {
    NghNeighborhood getNeighborhoodByDistrictId(long districtId);
}
