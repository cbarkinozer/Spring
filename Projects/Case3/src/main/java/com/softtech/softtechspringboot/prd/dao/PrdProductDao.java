package com.softtech.softtechspringboot.prd.dao;

import com.softtech.softtechspringboot.prd.entity.PrdProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrdProductDao extends JpaRepository<PrdProduct,Long> {
}
