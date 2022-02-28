package com.softtech.softtechspringboot.bld.dao;

import com.softtech.softtechspringboot.bld.entity.BldBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BldBuildingDao extends JpaRepository<BldBuilding,Long> {
}
