package com.softtech.softtechspringboot.usr.dto;

import com.softtech.softtechspringboot.usr.enums.UsrUserType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UsrUserSaveDto {

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private UsrUserType userType;
}
