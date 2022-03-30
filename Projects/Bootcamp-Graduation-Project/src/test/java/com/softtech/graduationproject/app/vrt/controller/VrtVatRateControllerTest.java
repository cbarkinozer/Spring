package com.softtech.graduationproject.app.vrt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softtech.graduationproject.GraduationProjectApplication;
import com.softtech.graduationproject.app.BaseTest;
import com.softtech.graduationproject.app.config.H2TestProfileJPAConfig;
import com.softtech.graduationproject.app.vrt.dto.VrtVatRateSaveRequestDto;
import com.softtech.graduationproject.app.vrt.dto.VrtVatRateUpdateRequestDto;
import com.softtech.graduationproject.app.vrt.enums.VrtProductType;
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
class VrtVatRateControllerTest extends BaseTest {

    private static final String BASE_PATH = "/api/v1/vat-rates";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void findAllVatRates() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void saveVatRate() throws Exception {

        VrtVatRateSaveRequestDto vrtVatRateSaveRequestDto
                = new VrtVatRateSaveRequestDto(VrtProductType.FOOD,1);

        String content = objectMapper.writeValueAsString(vrtVatRateSaveRequestDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH+"/save-vat-rate").content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void updateVatRate() throws Exception {

        VrtVatRateUpdateRequestDto vrtVatRateUpdateRequestDto
                = new VrtVatRateUpdateRequestDto(1L,VrtProductType.FOOD,2);

        String content = objectMapper.writeValueAsString(vrtVatRateUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH+"/update-vat-rate").content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }

    @Test
    void deleteVatRate() throws Exception{

        MvcResult result = mockMvc.perform(
                delete(BASE_PATH +"/delete-vat-rate/"+"/1").content("1L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
}