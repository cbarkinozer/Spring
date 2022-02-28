package com.softtech.softtechspringboot.cty.dto;

import lombok.Data;


@Data
public class CtyCityDto {
    private Long id;
    private Long cityId;
    private String cityName;
    private String cityCode;
}
