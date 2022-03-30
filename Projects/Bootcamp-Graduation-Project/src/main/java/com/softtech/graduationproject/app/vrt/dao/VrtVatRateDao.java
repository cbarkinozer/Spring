package com.softtech.graduationproject.app.vrt.dao;

import com.softtech.graduationproject.app.usr.entity.UsrUser;
import com.softtech.graduationproject.app.vrt.entity.VrtVatRate;
import com.softtech.graduationproject.app.vrt.enums.VrtProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VrtVatRateDao extends JpaRepository<VrtVatRate,Long> {

    Optional<VrtVatRate> findByProductType(VrtProductType vrtProductType);

    Optional<VrtVatRate> findVrtVatRateById(Long id);
}
