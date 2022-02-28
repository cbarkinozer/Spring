package com.softtech.softtechspringboot.str.dao;

import com.softtech.softtechspringboot.str.entity.StrStreet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StrStreetDao extends JpaRepository<StrStreet,Long> {
    List<StrStreet> getStreetByNeighborhoodId(long neighborhoodId);
}
