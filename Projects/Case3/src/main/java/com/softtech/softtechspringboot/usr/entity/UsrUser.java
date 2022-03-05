package com.softtech.softtechspringboot.usr.entity;

import com.softtech.softtechspringboot.gen.entity.BaseEntity;
import com.softtech.softtechspringboot.usr.enums.UsrUserType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="USR_USER")
@Getter
@Setter
public class UsrUser extends BaseEntity {

    @Id
    @SequenceGenerator(name ="UsrUser" ,sequenceName = "USR_USER_ID_SEQ")
    @GeneratedValue(generator = "UsrUser")
    private Long id;

    @Column(name="USERNAME",length=30,unique=true,nullable = false)
    private String username;

    @Column(name="EMAIL",length=30,unique = true,nullable = false)
    private String email;

    @Column(name="PHONE_NUMBER",length=15,unique=true,nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="USER_TYPE",nullable = false)
    private UsrUserType userType;

}
