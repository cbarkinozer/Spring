package com.softtech.graduationproject.app.vrt.entity;

import com.softtech.graduationproject.app.gen.entity.BaseEntity;
import com.softtech.graduationproject.app.vrt.enums.VrtProductType;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="VRT_VAT_RATE")
public class VrtVatRate extends BaseEntity {

    @Id
    @SequenceGenerator(name = "VrtVatRate" , sequenceName = "VRT_VAT_RATE_ID_SEQ")
    @GeneratedValue(generator = "VrtVatRate")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="PRODUCT_TYPE",length=30, nullable = false, unique = true)
    private VrtProductType productType;

    @Column(name="VAT_RATE",nullable = false)
    private Integer vatRate;

}
