package com.softtech.softtechspringboot.cnt.dao;

import com.softtech.softtechspringboot.cnt.entity.CntCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CntCountryDao extends JpaRepository<CntCountry,Long> {
    CntCountry getCountryByCountryCode(String countryCode);

}
