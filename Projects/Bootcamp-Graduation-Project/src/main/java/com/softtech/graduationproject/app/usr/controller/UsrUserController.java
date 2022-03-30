package com.softtech.graduationproject.app.usr.controller;

import com.softtech.graduationproject.app.gen.dto.RestResponse;
import com.softtech.graduationproject.app.usr.dto.UsrUserDto;
import com.softtech.graduationproject.app.usr.dto.UsrUserFindByIdRequestDto;
import com.softtech.graduationproject.app.usr.dto.UsrUserSaveRequestDto;
import com.softtech.graduationproject.app.usr.dto.UsrUserUpdateRequestDto;
import com.softtech.graduationproject.app.usr.service.UsrUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UsrUserController {

    private final UsrUserService usrUserService;


    @Operation(
            tags = "User Controller",
            summary = "All users",
            description = "Gets all users (only actives)."

    )
    @GetMapping
    public ResponseEntity<RestResponse<List<UsrUserDto>>> findAllUsers(){

        List<UsrUserDto> usrUserDtoList = usrUserService.findAllUsers();

        return ResponseEntity.ok(RestResponse.of(usrUserDtoList));
    }


    @Operation(
            tags = "User Controller",
            summary = "Get a user",
            description = "Gets a user by id (even if it is passive)."
    )
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UsrUserFindByIdRequestDto>> findUserById(@PathVariable Long id){

        UsrUserFindByIdRequestDto usrUserFindByIdRequestDto = usrUserService.findUserById(id);

        return ResponseEntity.ok(RestResponse.of(usrUserFindByIdRequestDto));
    }


    @Operation(
            tags="User Controller",
            summary = "Save a user",
            description = "Saves a new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Users",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UsrUserSaveRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new user",
                                                    summary = "New User Example",
                                                    description = "Complete request with all available fields for user",
                                                    value = "{\n" +
                                                            "  \"name\": \"john\",\n" +
                                                            "  \"surname\": \"smith\",\n" +
                                                            "  \"username\": \"john_smith\",\n" +
                                                            "  \"password\": \"J.s_1234\"\n" +
                                                            "}"
                                            )
                                    }
                            ),
                    }
            )
    )
    @PostMapping("/save-user")
    public ResponseEntity<RestResponse<MappingJacksonValue>> saveUser(@RequestBody UsrUserSaveRequestDto usrUserSaveRequestDto){

        UsrUserDto usrUserDto = usrUserService.saveUser(usrUserSaveRequestDto);

        MappingJacksonValue mappingJacksonValue = createLinksForSave(usrUserDto);

        return ResponseEntity.ok(RestResponse.of(mappingJacksonValue));

    }

    private MappingJacksonValue createLinksForSave(UsrUserDto usrUserDto){

        WebMvcLinkBuilder linkGet = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                        this.getClass()).findUserById(usrUserDto.getId()));

        WebMvcLinkBuilder linkDelete = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                        this.getClass()).cancelUser(usrUserDto.getId()));

        EntityModel<UsrUserDto> entityModel = EntityModel.of(usrUserDto);

        entityModel.add(linkGet.withRel("find-by-id"));
        entityModel.add(linkDelete.withRel("cancel"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entityModel);

        return mappingJacksonValue;
    }


    @Operation(
            tags="User Controller",
            summary = "Update a user",
            description = "Updates a user's name, surname, username, and password by id"
    )
    @PutMapping("/update-user")
    public ResponseEntity<RestResponse<UsrUserDto>> updateUser(UsrUserUpdateRequestDto usrUserUpdateRequestDto){

        UsrUserDto usrUserDto = usrUserService.updateUser(usrUserUpdateRequestDto);

        return ResponseEntity.ok(RestResponse.of(usrUserDto));

    }

    @Operation(
            tags="User Controller",
            summary = "Cancel a user",
            description = "Deletes a user by canceling (setting the status type passive) by id"
    )
    @PatchMapping("/cancel-user/{id}")
    public ResponseEntity<RestResponse<?>> cancelUser(@PathVariable Long id){

        usrUserService.cancelUser(id);

        return ResponseEntity.ok(RestResponse.empty());
    }

}
