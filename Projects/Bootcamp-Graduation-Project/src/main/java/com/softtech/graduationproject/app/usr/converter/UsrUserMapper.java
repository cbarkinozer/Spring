package com.softtech.graduationproject.app.usr.converter;

import com.softtech.graduationproject.app.usr.dto.UsrUserDto;
import com.softtech.graduationproject.app.usr.dto.UsrUserFindByIdRequestDto;
import com.softtech.graduationproject.app.usr.dto.UsrUserSaveRequestDto;
import com.softtech.graduationproject.app.usr.dto.UsrUserUpdateRequestDto;
import com.softtech.graduationproject.app.usr.entity.UsrUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsrUserMapper {

    UsrUserMapper INSTANCE = Mappers.getMapper(UsrUserMapper.class);

    UsrUser convertToUsrUser(UsrUserSaveRequestDto usrUserSaveRequestDto);

    UsrUser convertToUsrUser(UsrUserUpdateRequestDto usrUserUpdateRequestDto);

    UsrUserFindByIdRequestDto convertToUsrUserFindByIdRequestDto(UsrUser usrUser);

    List<UsrUserDto> convertToUsrUserDtoList(List<UsrUser> usrUserList);

    UsrUserDto convertToUsrUserDto(UsrUser cusCustomer);

}
