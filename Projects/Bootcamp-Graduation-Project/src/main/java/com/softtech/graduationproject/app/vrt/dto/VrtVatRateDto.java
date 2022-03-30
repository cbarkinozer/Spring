package com.softtech.graduationproject.app.vrt.dto;

import com.softtech.graduationproject.app.vrt.enums.VrtProductType;
import lombok.Data;


@Data
public class VrtVatRateDto {

    private Long id;
    private VrtProductType productType;
    private Integer vatRate;

}
