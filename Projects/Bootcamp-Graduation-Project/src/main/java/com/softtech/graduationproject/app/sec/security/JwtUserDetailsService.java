package com.softtech.graduationproject.app.sec.security;

import com.softtech.graduationproject.app.gen.exceptions.ItemNotFoundException;
import com.softtech.graduationproject.app.usr.entity.UsrUser;
import com.softtech.graduationproject.app.usr.enums.UsrErrorMessage;
import com.softtech.graduationproject.app.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UsrUserEntityService usrUserEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsrUser usrUser = usrUserEntityService.findUsersByUsername(username)
                .orElseThrow(()-> new ItemNotFoundException(UsrErrorMessage.USER_NOT_FOUND));

        return JwtUserDetails.create(usrUser);
    }

    public UserDetails loadUserByUserId(Long id) {

        UsrUser usrUser = usrUserEntityService.getByIdWithControl(id);

        return JwtUserDetails.create(usrUser);
    }
}
