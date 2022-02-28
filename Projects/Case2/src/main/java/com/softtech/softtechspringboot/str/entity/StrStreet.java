package com.softtech.softtechspringboot.str.entity;

import com.softtech.softtechspringboot.ngh.entity.NghNeighborhood;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="STR_STREET")
public class StrStreet {
    @Id
    @SequenceGenerator(name="StrStreet",sequenceName = "STR_STREET_ID_SEQ")
    @GeneratedValue(generator="StrStreet")
    private Long id;
    @Column(name="STR_STREET_ID",nullable = false)
    private String streetId;
    @Column(name="STR_STREET_NAME",nullable = true)
    private String streetName;

    @ManyToOne(
            fetch= FetchType.LAZY,
            cascade=CascadeType.ALL,
            optional=false)

    private NghNeighborhood nghNeighborhood;


}
