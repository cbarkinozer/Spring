package com.softtech.softtechspringboot.dor.dao;

import com.softtech.softtechspringboot.dor.entity.DorDoor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DorDoorDao extends JpaRepository<DorDoor,Long> {
}
