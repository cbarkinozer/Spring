package com.softtech.softtechspringboot.cmt.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class CmtCommentDto {

    @NotNull
    private String comment;

    @NotNull
    private Long productId;

    @NotNull
    private Long userId;
}
