package com.softtech.softtechspringboot.usr.converter;


import com.softtech.softtechspringboot.usr.dto.UsrUserDto;
import com.softtech.softtechspringboot.usr.dto.UsrUserSaveDto;
import com.softtech.softtechspringboot.usr.dto.UsrUserUpdateDto;
import com.softtech.softtechspringboot.usr.entity.UsrUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsrUserMapper {

    UsrUserMapper INSTANCE = Mappers.getMapper(UsrUserMapper.class);

    UsrUser convertToUsrUser(UsrUserDto usrUserDto);
    UsrUser convertToUsrUser(UsrUserSaveDto usrUserSaveDto);
    UsrUser convertToUsrUser(UsrUserUpdateDto usrUserUpdateDto);

    List<UsrUserDto> convertToUsrUserDtoList(List<UsrUser> usrUserList);
    UsrUserDto convertToUsrUserDto(UsrUser usrUser);

}
