package com.softtech.softtechspringboot.cnt.dto;

import lombok.Data;

@Data
public class CntCountryDto {
    private Long id;
    private Long countryId;
    private String countryName;
    private String countryCode;
}
