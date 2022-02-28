package com.softtech.softtechspringboot.ngh.entity;

import com.softtech.softtechspringboot.dst.entity.DstDistrict;
import com.softtech.softtechspringboot.str.entity.StrStreet;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name="NGH_NEIGHBORHOOD")
@RequiredArgsConstructor
public class NghNeighborhood {
    @Id
    @SequenceGenerator(name="NghNeighborhood",sequenceName = "NGH_NEIGHBORHOOD_ID_SEQ")
    @GeneratedValue(generator="NghNeighborhood")
    private Long id;

    @Column(name="NGH_NEIGHBORHOOD_ID",nullable = false)
    private Long neighborhoodId;

    @Column(name="NGH_NEIGHBORHOOD_NAME",nullable = false)
    private String neighborhoodName;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private DstDistrict dstDistrict;

    @OneToMany(mappedBy = "NghNeighborhood",fetch = FetchType.LAZY)
    private List<StrStreet> strStreetList;

}
