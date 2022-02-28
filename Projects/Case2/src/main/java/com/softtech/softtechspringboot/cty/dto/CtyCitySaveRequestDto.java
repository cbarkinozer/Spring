package com.softtech.softtechspringboot.cty.dto;

import lombok.Data;

@Data
public class CtyCitySaveRequestDto {
    private Long cityId;
    private String cityName;
    private String cityCode;
}
