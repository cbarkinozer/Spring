package com.softtech.softtechspringboot.add.dto;

import lombok.Data;

@Data
public class AddAddressSaveRequestDto {
    private Long countryId;
    private Long cityId;
    private Long districtId;
    private Long neighborhoodId;
    private Long streetId;
    private Long buildingId;
    private Long doorId;
}
