package com.softtech.graduationproject.app.usr.service;

import com.softtech.graduationproject.app.gen.exceptions.IllegalFieldException;
import com.softtech.graduationproject.app.usr.dto.UsrUserDto;
import com.softtech.graduationproject.app.usr.dto.UsrUserFindByIdRequestDto;
import com.softtech.graduationproject.app.usr.dto.UsrUserSaveRequestDto;
import com.softtech.graduationproject.app.usr.dto.UsrUserUpdateRequestDto;
import com.softtech.graduationproject.app.usr.entity.UsrUser;
import com.softtech.graduationproject.app.usr.service.entityservice.UsrUserEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsrUserServiceTest {

    @InjectMocks
    private UsrUserService usrUserService;

    @Mock
    private UsrUserEntityService usrUserEntityService;

    @Mock
    private UsrUserValidationService usrUserValidationService;

    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    void findAllUsers() {

        UsrUser usrUser = mock(UsrUser.class);
        List<UsrUser> usrUserList = new ArrayList<>();
        usrUserList.add(usrUser);

        when(usrUserEntityService.findAllActiveUsers()).thenReturn(usrUserList);

        List<UsrUserDto> result = usrUserService.findAllUsers();

        assertEquals(1, result.size());
    }

    @Test
    void findUserById() {

        Long id = 1L;

        UsrUser usrUser = mock(UsrUser.class);
        when(usrUser.getId()).thenReturn(id);

        when(usrUserEntityService.getByIdWithControl(id)).thenReturn(usrUser);

        UsrUserFindByIdRequestDto usrUserFindByIdRequestDto = usrUserService.findUserById(id);

        assertEquals(id, usrUserFindByIdRequestDto.getId());
    }

    @Test
    void saveUser() {

        UsrUserSaveRequestDto usrUserSaveRequestDto = mock(UsrUserSaveRequestDto.class);

        UsrUser usrUser = mock(UsrUser.class);

        when(usrUser.getId()).thenReturn(1L);

        when(usrUserEntityService.save(any())).thenReturn(usrUser);

        UsrUserDto result = usrUserService.saveUser(usrUserSaveRequestDto);

        assertEquals(1L, result.getId());
    }

    @Test
    void dontSaveUser_WhenUsername_IsNotUnique(){

        UsrUserSaveRequestDto usrUserSaveRequestDto = mock(UsrUserSaveRequestDto.class);

        UsrUser usrUser = mock(UsrUser.class);

        when(usrUserEntityService.save(usrUser)).thenReturn(usrUser);

        assertThrows(IllegalFieldException.class, () -> usrUserService.saveUser(usrUserSaveRequestDto));

        verify(usrUserValidationService).controlIsUsernameUnique(usrUser);
    }


    @Test
    void updateUser() {

        Long id = 1L;

        UsrUserUpdateRequestDto usrUserUpdateRequestDto = mock(UsrUserUpdateRequestDto.class);
        UsrUser usrUser = mock(UsrUser.class);
        when(usrUser.getId()).thenReturn(id);

        doNothing().when(usrUserValidationService).controlIsUserExist(anyLong());

        when(usrUserEntityService.save(any())).thenReturn(usrUser);

        UsrUserDto usrUserDto = usrUserService.updateUser(usrUserUpdateRequestDto);

        assertEquals(id, usrUserDto.getId());
    }

    @Test
    void dontUpdateUser_WhenUsername_IsNotUnique(){

        UsrUserUpdateRequestDto usrUserUpdateRequestDto = mock(UsrUserUpdateRequestDto.class);
        UsrUser usrUser = mock(UsrUser.class);

        doThrow(IllegalFieldException.class).when(usrUserValidationService)
                .controlIsUsernameUnique(usrUser);

        assertThrows(IllegalFieldException.class,()->usrUserService.updateUser(usrUserUpdateRequestDto));

        verify(usrUserValidationService).controlIsUsernameUnique(usrUser);

    }

    @Test
    void cancelUser() {

        UsrUser usrUser = mock(UsrUser.class);

        when(usrUserEntityService.getByIdWithControl(anyLong())).thenReturn(usrUser);

        usrUserService.cancelUser(anyLong());

        verify(usrUserEntityService).getByIdWithControl(anyLong());
        verify(usrUserEntityService).save(any());
    }
}