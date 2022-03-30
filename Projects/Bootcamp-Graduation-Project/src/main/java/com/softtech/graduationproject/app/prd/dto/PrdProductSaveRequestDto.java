package com.softtech.graduationproject.app.prd.dto;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class PrdProductSaveRequestDto {

    private Long usrUserId;
    private Long vrtVatRateId;
    private String name;
    private BigDecimal vatFreePrice;
}
