package com.softtech.softtechspringboot.cty.entity;

import com.softtech.softtechspringboot.cnt.entity.CntCountry;
import com.softtech.softtechspringboot.dst.entity.DstDistrict;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@Table(name="CTY_CITY")
public class CtyCity {
    @Id
    @SequenceGenerator(name="CtyCity",sequenceName = "CTY_CITY_ID_SEQ")
    @GeneratedValue(generator="CtyCity")
    private Long id;

    @Column(name="CTY_CITY_ID",nullable = false)
    private Long cityId;

    @Column(name="CTY_CITY_NAME",nullable = false)
    private String cityName;

    @Column(name="CTY_CITY_CODE",nullable = false)
    private Long cityCode;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private CntCountry cntCountry;

    @OneToMany(mappedBy = "city",fetch = FetchType.LAZY)
    private List<DstDistrict> dstDistrictList;


}
