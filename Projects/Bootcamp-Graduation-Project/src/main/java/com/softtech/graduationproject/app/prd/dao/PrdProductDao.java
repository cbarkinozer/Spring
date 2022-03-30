package com.softtech.graduationproject.app.prd.dao;

import com.softtech.graduationproject.app.prd.dto.PrdProductAnalysisRequestDto;
import com.softtech.graduationproject.app.prd.dto.PrdVatRateDto;
import com.softtech.graduationproject.app.prd.entity.PrdProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PrdProductDao extends JpaRepository<PrdProduct,Long> {


    List<PrdProduct> findAllByPriceBetween(BigDecimal min, BigDecimal max);


    List<PrdProduct> findAllByVrtVatRateId(Long vrtVatRateId);


    @Query( value = "SELECT "+
                    "new com.softtech.graduationproject.app.prd.dto.PrdProductAnalysisRequestDto( " +
                    "vrt.productType,"+
                    "vrt.vatRate,"+
                    "min(prd.price),"+
                    "max(prd.price),"+
                    "avg(prd.price),"+
                    "count(prd)" +
                    " ) " +
                    "FROM PrdProduct prd "+
                    "LEFT JOIN VrtVatRate vrt "+
                    "ON prd.vrtVatRateId = vrt.id " +
                    "WHERE prd.vrtVatRateId = vrt.id "+
                    "GROUP BY vrt.productType,vrt.vatRate"
    )
    List<PrdProductAnalysisRequestDto> getProductAnalysis();

}
