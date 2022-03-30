package com.softtech.graduationproject.app.vrt.dto;

import com.softtech.graduationproject.app.vrt.enums.VrtProductType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class VrtVatRateUpdateRequestDto {

    private Long id;
    private VrtProductType productType;
    private Integer vatRate;

}
