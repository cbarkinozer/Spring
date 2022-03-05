package com.softtech.softtechspringboot.cmt.entity;


import com.softtech.softtechspringboot.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="CMT_COMMENT")
@Getter @Setter
public class CmtComment extends BaseEntity {

    @Id
    @SequenceGenerator(name ="CMT_COMMENT" ,sequenceName = "CMT_COMMENT_ID_SEQ")
    @GeneratedValue(generator ="CMT_COMMENT")
    private Long id;

    @Lob
    @Column(name="COMMENT",nullable = false)
    private String comment;

    @Column(name="ID_PRD_PRODUCT",nullable = false)
    private Long productId;

    @Column(name="ID_USR_USER",nullable = false)
    private Long userId;
}
