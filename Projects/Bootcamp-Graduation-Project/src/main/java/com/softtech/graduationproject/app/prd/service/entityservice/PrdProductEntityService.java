package com.softtech.graduationproject.app.prd.service.entityservice;

import com.softtech.graduationproject.app.gen.service.BaseEntityService;
import com.softtech.graduationproject.app.prd.dao.PrdProductDao;
import com.softtech.graduationproject.app.prd.dto.PrdProductAnalysisRequestDto;
import com.softtech.graduationproject.app.prd.entity.PrdProduct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PrdProductEntityService extends BaseEntityService<PrdProduct,PrdProductDao> {


    public PrdProductEntityService(PrdProductDao prdProductDao){

        super(prdProductDao);
    }


    public List<PrdProduct> findProductsByPriceInterval(BigDecimal min, BigDecimal max) {

        List<PrdProduct> prdProductList = getDao().findAllByPriceBetween(min,max);

        return prdProductList;
    }


    public List<PrdProduct> findProductsByVatRateId(Long vrtVatRateId) {

        List<PrdProduct> prdProductList = getDao().findAllByVrtVatRateId(vrtVatRateId);

        return prdProductList;
    }


    public List<PrdProductAnalysisRequestDto> getProductAnalysis() {

        List<PrdProductAnalysisRequestDto> prdProductAnalysisRequestDtoList = getDao().getProductAnalysis();

        return prdProductAnalysisRequestDtoList;
    }


}
