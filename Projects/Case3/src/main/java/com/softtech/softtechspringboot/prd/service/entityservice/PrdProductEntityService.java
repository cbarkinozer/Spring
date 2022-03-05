package com.softtech.softtechspringboot.prd.service.entityservice;

import com.softtech.softtechspringboot.gen.exceptions.ItemNotFoundException;
import com.softtech.softtechspringboot.gen.service.BaseEntityService;
import com.softtech.softtechspringboot.prd.dao.PrdProductDao;
import com.softtech.softtechspringboot.prd.entity.PrdProduct;
import com.softtech.softtechspringboot.prd.enums.PrdErrorMessage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PrdProductEntityService extends BaseEntityService<PrdProduct, PrdProductDao> {

    private final PrdProductDao prdProductDao;

    public PrdProductEntityService(PrdProductDao prdProductDao){
        super(prdProductDao);
        this.prdProductDao = prdProductDao;
    }

    public PrdProduct findById(Long id){

        PrdProduct prdProduct = prdProductDao.findById(id)
                .orElseThrow(()-> new ItemNotFoundException(PrdErrorMessage.PRODUCT_NOT_FOUND));

        return prdProduct;
    }

    public PrdProduct updatePrice(Long id, BigDecimal price) {

        PrdProduct prdProduct = prdProductDao.findById(id)
                .orElseThrow(()->new ItemNotFoundException(PrdErrorMessage.PRODUCT_NOT_FOUND));

        prdProduct.setPrice(price);
        return prdProductDao.save(prdProduct);
    }

}
