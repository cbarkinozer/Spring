package com.softtech.graduationproject.app.sec.controller;


import com.softtech.graduationproject.app.gen.dto.RestResponse;
import com.softtech.graduationproject.app.sec.dto.SecLoginRequestDto;
import com.softtech.graduationproject.app.sec.service.AuthenticationService;
import com.softtech.graduationproject.app.usr.dto.UsrUserDto;
import com.softtech.graduationproject.app.usr.dto.UsrUserSaveRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService authenticationService;


    @Operation(tags = "Authentication Controller")
    @PostMapping("/login")
    public ResponseEntity<RestResponse<String>> login(@RequestBody SecLoginRequestDto secLoginRequestDto){

        String token = authenticationService.login(secLoginRequestDto);

        return ResponseEntity.ok(RestResponse.of(token));
    }


    @Operation(tags = "Authentication Controller")
    @PostMapping("/register")
    public ResponseEntity<RestResponse<UsrUserDto>> register(@RequestBody UsrUserSaveRequestDto usrUserSaveRequestDto){

        UsrUserDto usrUserDto =authenticationService.register(usrUserSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(usrUserDto));
    }
}
