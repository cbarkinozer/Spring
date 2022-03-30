package com.softtech.graduationproject.app.prd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softtech.graduationproject.GraduationProjectApplication;
import com.softtech.graduationproject.app.BaseTest;
import com.softtech.graduationproject.app.config.H2TestProfileJPAConfig;
import com.softtech.graduationproject.app.prd.dto.PrdProductSaveRequestDto;
import com.softtech.graduationproject.app.prd.dto.PrdProductUpdateRequestDto;
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


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {GraduationProjectApplication.class, H2TestProfileJPAConfig.class})
class PrdProductControllerTest extends BaseTest {

    private static final String BASE_PATH = "/api/v1/products";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void findAllProducts() throws Exception{

        MvcResult result = mockMvc.perform(
                get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void findProductsById() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/1").content("1L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void findProductsByPriceInterval() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/find-by-price-interval"+"?min=1&max=3").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }

    @Test
    void findProductsByProductType() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/find-by-product-type"+"?vrtProductType=FOOD").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void getProductAnalysis() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/get-product-analysis").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void saveProduct() throws Exception{

        PrdProductSaveRequestDto prdProductSaveRequestDto =
                new PrdProductSaveRequestDto(1L,1L,"Beef Cube Meat Kg",BigDecimal.valueOf(100));


        String content = objectMapper.writeValueAsString(prdProductSaveRequestDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH+"/save-product").content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void updateProduct() throws Exception {

        PrdProductUpdateRequestDto prdProductUpdateRequestDto =
                new PrdProductUpdateRequestDto(1L,1L,1L,"Beef Cube Meat Kg",BigDecimal.valueOf(100));

        String content = objectMapper.writeValueAsString(prdProductUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH+"/update-product").content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void deleteProduct() throws Exception {

        MvcResult result = mockMvc.perform(
                delete(BASE_PATH +"/delete-product/"+"/1").content("1L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
}