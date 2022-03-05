package com.softtech.softtechspringboot.usr.service;

import com.softtech.softtechspringboot.usr.converter.UsrUserMapper;
import com.softtech.softtechspringboot.usr.dto.UsrUserDto;
import com.softtech.softtechspringboot.usr.dto.UsrUserSaveDto;
import com.softtech.softtechspringboot.usr.dto.UsrUserUpdateDto;
import com.softtech.softtechspringboot.usr.entity.UsrUser;
import com.softtech.softtechspringboot.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsrUserService {

    private final UsrUserEntityService usrUserEntityService;

    public List<UsrUserDto> findAll() {
        List<UsrUser> usrUserList = usrUserEntityService.findAll();
        return UsrUserMapper.INSTANCE.convertToUsrUserDtoList(usrUserList);
    }
    public UsrUserDto findById(Long id){
        UsrUser usrUser = usrUserEntityService.findByIdWithControl(id);
        return UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);
    }
    public UsrUserDto findByUsername(String username){
        UsrUser usrUser = usrUserEntityService.getByUsernameWithControl(username);
        return UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);
    }

    public UsrUserDto save(UsrUserSaveDto usrUserSaveDto) {
        UsrUser usrUser = UsrUserMapper.INSTANCE.convertToUsrUser(usrUserSaveDto);
        usrUser = usrUserEntityService.saveWithControl(usrUser);
        return UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);
    }

    public UsrUserDto update(UsrUserUpdateDto usrUserUpdateDto) {
        UsrUser usrUser = UsrUserMapper.INSTANCE.convertToUsrUser(usrUserUpdateDto);
        usrUser = usrUserEntityService.update(usrUser);
        return UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);
    }

    public void delete(String username, String phoneNumber) {
        usrUserEntityService.deleteWithControl(username,phoneNumber);
    }
}
