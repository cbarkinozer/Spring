package com.softtech.graduationproject.app.usr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softtech.graduationproject.GraduationProjectApplication;
import com.softtech.graduationproject.app.BaseTest;
import com.softtech.graduationproject.app.config.H2TestProfileJPAConfig;
import com.softtech.graduationproject.app.usr.dto.UsrUserSaveRequestDto;
import com.softtech.graduationproject.app.usr.dto.UsrUserUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {GraduationProjectApplication.class, H2TestProfileJPAConfig.class})
class UsrUserControllerTest extends BaseTest {

    private static final String BASE_PATH = "/api/v1/users";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void findAllUsers() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void findUserById() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/1").content("1L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void saveUser() throws Exception {


        UsrUserSaveRequestDto userUserSaveRequestDto =
                new UsrUserSaveRequestDto("John","Smith","smt","smt");


        String content = objectMapper.writeValueAsString(userUserSaveRequestDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH+"/save-user").content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void updateUser() throws Exception{

        UsrUserUpdateRequestDto usrUserUpdateRequestDto =
                new UsrUserUpdateRequestDto(1L,"John","Smith","smt","smt");


        String content = objectMapper.writeValueAsString(usrUserUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH+"/update-user").content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void cancelUser() throws Exception{

        MvcResult result = mockMvc.perform(
                delete(BASE_PATH +"/cancel-user"+"/1").content("1L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
}