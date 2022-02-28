package com.softtech.softtechspringboot.cnt.dto;

import lombok.Data;

@Data
public class CntCountrySaveRequestDto {
    private Long countryId;
    private String countryName;
    private String countryCode;
}
