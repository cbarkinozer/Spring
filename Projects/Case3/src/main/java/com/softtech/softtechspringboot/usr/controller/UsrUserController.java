package com.softtech.softtechspringboot.usr.controller;

import com.softtech.softtechspringboot.gen.dto.RestResponse;
import com.softtech.softtechspringboot.usr.dto.UsrUserDto;
import com.softtech.softtechspringboot.usr.dto.UsrUserSaveDto;
import com.softtech.softtechspringboot.usr.dto.UsrUserUpdateDto;
import com.softtech.softtechspringboot.usr.service.UsrUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsrUserController {

    private final UsrUserService usrUserService;

    @Operation(summary="Get all users.")
    @GetMapping
    public ResponseEntity findAll(){

        List<UsrUserDto> usrUserDtoList = usrUserService.findAll();
        return ResponseEntity.ok(RestResponse.of(usrUserDtoList));

    }

    @Operation(summary="Get a user by id.")
    @GetMapping("/id/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        UsrUserDto usrUserDto = usrUserService.findById(id);
        return ResponseEntity.ok(RestResponse.of(usrUserDto));

    }

    @Operation(summary="Get a user by unique username.")
    @GetMapping("/username/{username}")
    public ResponseEntity findByUsername(@PathVariable String username){

        UsrUserDto usrUserDto = usrUserService.findByUsername(username);
        return ResponseEntity.ok(RestResponse.of(usrUserDto));

    }

    @Operation(summary="Save a user.")
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody UsrUserSaveDto usrUserSaveDto){

        UsrUserDto usrUserDtoSaved = usrUserService.save(usrUserSaveDto);
        return ResponseEntity.ok(RestResponse.of(usrUserDtoSaved));

    }

    @Operation(summary="Update a user.")
    @PatchMapping("/update")
    public ResponseEntity update(@RequestBody UsrUserUpdateDto usrUserUpdateDto){

        UsrUserDto usrUserDtoUpdated = usrUserService.update(usrUserUpdateDto);
        return ResponseEntity.ok(RestResponse.of(usrUserDtoUpdated));

    }

    @Operation(summary="Delete a user.")
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam String username, String phoneNumber ) {
        usrUserService.delete(username,phoneNumber);
        return ResponseEntity.ok(RestResponse.empty());
    }

}
