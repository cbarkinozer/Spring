package com.softtech.softtechspringboot.add.entity;
import com.softtech.softtechspringboot.bld.entity.BldBuilding;
import com.softtech.softtechspringboot.cnt.entity.CntCountry;
import com.softtech.softtechspringboot.cty.entity.CtyCity;
import com.softtech.softtechspringboot.dor.entity.DorDoor;
import com.softtech.softtechspringboot.dst.entity.DstDistrict;
import com.softtech.softtechspringboot.ngh.entity.NghNeighborhood;
import com.softtech.softtechspringboot.str.entity.StrStreet;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ADD_ADDRESS")
@Data
public class AddAddress {
    @Id
    @SequenceGenerator(name="AddAddress",sequenceName = "ADD_ADDRESS_ID_SEQ")
    @GeneratedValue(generator="AddAddress")
    private Long id;
    @ManyToOne
    private CntCountry cntCountry;
    @ManyToOne
    private CtyCity ctyCity;
    @ManyToOne
    private DstDistrict dstDistrict;
    @ManyToOne
    private NghNeighborhood nghNeighborhood;
    @ManyToOne
    private StrStreet strStreet;
    @ManyToOne
    private BldBuilding bldBuilding;
    @ManyToOne
    private DorDoor dorDoor;


}
