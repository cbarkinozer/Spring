package com.softtech.graduationproject.app.usr.service;

import com.softtech.graduationproject.app.gen.enums.GenStatusType;
import com.softtech.graduationproject.app.usr.converter.UsrUserMapper;
import com.softtech.graduationproject.app.usr.dto.UsrUserDto;
import com.softtech.graduationproject.app.usr.dto.UsrUserFindByIdRequestDto;
import com.softtech.graduationproject.app.usr.dto.UsrUserSaveRequestDto;
import com.softtech.graduationproject.app.usr.dto.UsrUserUpdateRequestDto;
import com.softtech.graduationproject.app.usr.entity.UsrUser;
import com.softtech.graduationproject.app.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UsrUserService {

    private final UsrUserEntityService usrUserEntityService;
    private final UsrUserValidationService usrUserValidationService;

    private final PasswordEncoder passwordEncoder;


    public List<UsrUserDto> findAllUsers() {

        List<UsrUser> usrUserList = usrUserEntityService.findAllActiveUsers();

        List<UsrUserDto> usrUserDtoList = UsrUserMapper.INSTANCE.convertToUsrUserDtoList(usrUserList);

        return usrUserDtoList;
    }

    public UsrUserFindByIdRequestDto findUserById(Long id) {

        UsrUser usrUser = usrUserEntityService.getByIdWithControl(id);

        UsrUserFindByIdRequestDto usrUserFindByIdRequestDto = UsrUserMapper.INSTANCE
                .convertToUsrUserFindByIdRequestDto(usrUser);

        return usrUserFindByIdRequestDto;
    }

    public UsrUserDto saveUser(UsrUserSaveRequestDto usrUserSaveRequestDto){

        UsrUser usrUser = UsrUserMapper.INSTANCE.convertToUsrUser(usrUserSaveRequestDto);

        String password = passwordEncoder.encode(usrUser.getPassword());
        usrUser.setPassword(password);

        usrUserValidationService.controlAreFieldsNonNull(usrUser);
        usrUserValidationService.controlIsUsernameUnique(usrUser);

        usrUser.setStatusType(GenStatusType.ACTIVE);
        usrUser = usrUserEntityService.save(usrUser);

        UsrUserDto usrUserDto = UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);

        return usrUserDto;
    }

    public UsrUserDto updateUser(UsrUserUpdateRequestDto usrUserUpdateRequestDto) {

        Long id = usrUserUpdateRequestDto.getId();

        usrUserValidationService.controlIsUserExist(id);

        UsrUser usrUser = UsrUserMapper.INSTANCE.convertToUsrUser(usrUserUpdateRequestDto);

        usrUserValidationService.controlAreFieldsNonNull(usrUser);
        usrUserValidationService.controlIsUsernameUnique(usrUser);

        usrUser.setStatusType(GenStatusType.ACTIVE);
        usrUser = usrUserEntityService.save(usrUser);

        UsrUserDto usrUserDto = UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);

        return usrUserDto;

    }


    public void cancelUser(Long id) {

        UsrUser usrUser = usrUserEntityService.getByIdWithControl(id);

        usrUser.setStatusType(GenStatusType.PASSIVE);
        usrUserEntityService.save(usrUser);
    }
}
