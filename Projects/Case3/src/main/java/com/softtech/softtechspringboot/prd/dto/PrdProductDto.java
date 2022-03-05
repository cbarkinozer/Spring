package com.softtech.softtechspringboot.prd.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PrdProductDto {

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

}
