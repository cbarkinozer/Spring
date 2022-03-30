package com.softtech.graduationproject.app.usr.dto;

import com.softtech.graduationproject.app.gen.enums.GenStatusType;
import lombok.Data;

@Data
public class UsrUserFindByIdRequestDto {

    private Long id;
    private String name;
    private String surname;
    private String username;
    private GenStatusType statusType;
}
