package com.softtech.softtechspringboot.cnt.entity;

import com.softtech.softtechspringboot.cty.entity.CtyCity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@Table(name="CNT_COUNTRY")
public class CntCountry {
    @Id
    @SequenceGenerator(name="CntCountry",sequenceName = "CNT_COUNTRY_ID_SEQ")
    @GeneratedValue(generator="CntCountry")
    private Long id;

    @Column(name="CNT_COUNTRY_ID",nullable = false)
    private Long countryId;

    @Column(name="CTY_CITY_NAME",nullable = false)
    private String countryName;

    @Column(name="CNT_COUNTRY_CODE",length=5, nullable = false)
    private String countryCode;

    @OneToMany(mappedBy = "CntCountry",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CtyCity> cities;
}
