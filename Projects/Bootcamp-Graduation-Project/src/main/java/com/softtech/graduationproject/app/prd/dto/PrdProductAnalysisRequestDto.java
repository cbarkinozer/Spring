package com.softtech.graduationproject.app.prd.dto;

import com.softtech.graduationproject.app.vrt.enums.VrtProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PrdProductAnalysisRequestDto {

    private VrtProductType productType;
    private Integer vatRate;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Double avgPrice;
    private Long productCount;
}
