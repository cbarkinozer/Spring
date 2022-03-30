package com.softtech.graduationproject.app.usr.entity;

import com.softtech.graduationproject.app.gen.entity.BaseEntity;
import com.softtech.graduationproject.app.gen.enums.GenStatusType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="USR_USER")
public class UsrUser extends BaseEntity {
    @Id
    @SequenceGenerator(name = "UsrUser" , sequenceName = "USR_USER_ID_SEQ")
    @GeneratedValue(generator = "UsrUser")
    private Long id;

    @Column(name="NAME",length=100, nullable = false)
    private String name;

    @Column(name="SURNAME",length=100, nullable = false)
    private String surname;

    @Column(name="USERNAME",length=100,nullable = false,unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_TYPE", length = 30, nullable = false)
    private GenStatusType statusType;

}
