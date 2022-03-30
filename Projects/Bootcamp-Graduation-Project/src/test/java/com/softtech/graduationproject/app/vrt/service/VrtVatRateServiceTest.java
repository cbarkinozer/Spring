package com.softtech.graduationproject.app.vrt.service;


import com.softtech.graduationproject.app.gen.exceptions.IllegalFieldException;
import com.softtech.graduationproject.app.prd.service.PrdProductService;
import com.softtech.graduationproject.app.vrt.dto.VrtVatRateDto;
import com.softtech.graduationproject.app.vrt.dto.VrtVatRateSaveRequestDto;
import com.softtech.graduationproject.app.vrt.dto.VrtVatRateUpdateRequestDto;
import com.softtech.graduationproject.app.vrt.entity.VrtVatRate;
import com.softtech.graduationproject.app.vrt.service.entityservice.VrtVatRateEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VrtVatRateServiceTest {

    @InjectMocks
    private VrtVatRateService vrtVatRateService;

    @Mock
    private VrtVatRateEntityService vrtVatRateEntityService;

    @Mock
    private PrdProductService prdProductService;

    @Mock
    private VrtVatRateValidationService vrtVatRateValidationService;

    @Test
    void findAllVatRates() {

        VrtVatRate vrtVatRate = mock(VrtVatRate.class);
        List<VrtVatRate> vrtVatRateList = new ArrayList<>();
        vrtVatRateList.add(vrtVatRate);

        when(vrtVatRateEntityService.findAll()).thenReturn(vrtVatRateList);

        List<VrtVatRateDto> result = vrtVatRateService.findAllVatRates();

        assertEquals(1, result.size());
    }

    @Test
    void saveVatRate() {

        VrtVatRateSaveRequestDto vrtVatRateSaveRequestDto = mock(VrtVatRateSaveRequestDto.class);

        VrtVatRate vrtVatRate = mock(VrtVatRate.class);

        when(vrtVatRate.getId()).thenReturn(1L);

        when(vrtVatRateEntityService.save(any())).thenReturn(vrtVatRate);

        VrtVatRateDto result = vrtVatRateService.saveVatRate(vrtVatRateSaveRequestDto);

        assertEquals(1L, result.getId());

    }

    @Test
    void dontSaveVatRate_WhenVatRate_IsNegative(){

        VrtVatRateSaveRequestDto vrtVatRateSaveRequestDto = mock(VrtVatRateSaveRequestDto.class);

        VrtVatRate vrtVatRate = mock(VrtVatRate.class);

        when(vrtVatRate.getVatRate()).thenReturn(-1);

        doThrow(IllegalFieldException.class).when(vrtVatRateValidationService)
                .controlIsVatRateNegative(vrtVatRate);

        assertThrows(IllegalFieldException.class,()->vrtVatRateService.saveVatRate(vrtVatRateSaveRequestDto));

        verify(vrtVatRateValidationService).controlIsVatRateNegative(vrtVatRate);
    }

    @Test
    void dontSaveVatRate_WhenProductType_IsNotUnique(){

        VrtVatRateSaveRequestDto vrtVatRateSaveRequestDto = mock(VrtVatRateSaveRequestDto.class);

        VrtVatRate vrtVatRate = mock(VrtVatRate.class);

        doThrow(IllegalFieldException.class).when(vrtVatRateValidationService)
                .controlIsProductTypeUnique(vrtVatRate);

        assertThrows(IllegalFieldException.class,()->vrtVatRateService.saveVatRate(vrtVatRateSaveRequestDto));
        verify(vrtVatRateValidationService).controlIsProductTypeUnique(vrtVatRate);
    }

    @Test
    void updateVatRate() {

        Long id = 1L;

        VrtVatRateUpdateRequestDto vrtVatRateUpdateRequestDto = mock(VrtVatRateUpdateRequestDto.class);
        VrtVatRate vrtVatRate = mock(VrtVatRate.class);
        when(vrtVatRate.getId()).thenReturn(id);

        doNothing().when(vrtVatRateValidationService).controlIsVrtVatRateExist(anyLong());

        when(vrtVatRateEntityService.save(any())).thenReturn(vrtVatRate);

        VrtVatRateDto vrtVatRateDto = vrtVatRateService.updateVatRate(vrtVatRateUpdateRequestDto);

        assertEquals(id, vrtVatRateDto.getId());
    }

    @Test
    void dontUpdateVatRate_WhenVatRate_IsNegative(){


        VrtVatRateUpdateRequestDto vrtVatRateUpdateRequestDto = mock(VrtVatRateUpdateRequestDto.class);
        VrtVatRate vrtVatRate = mock(VrtVatRate.class);
        when(vrtVatRate.getVatRate()).thenReturn(-1);

        doThrow(IllegalFieldException.class).when(vrtVatRateValidationService)
                .controlIsVatRateNegative(vrtVatRate);

        assertThrows(IllegalFieldException.class,()->vrtVatRateService.updateVatRate(vrtVatRateUpdateRequestDto));

        verify(vrtVatRateValidationService).controlIsVatRateNegative(vrtVatRate);
    }

    @Test
    void dontUpdateVatRate_WhenProductType_IsNotUnique(){

        VrtVatRateUpdateRequestDto vrtVatRateUpdateRequestDto = mock(VrtVatRateUpdateRequestDto.class);
        VrtVatRate vrtVatRate = mock(VrtVatRate.class);

        doThrow(IllegalFieldException.class).when(vrtVatRateValidationService)
                .controlIsProductTypeUnique(vrtVatRate);

        assertThrows(IllegalFieldException.class,()->vrtVatRateService.updateVatRate(vrtVatRateUpdateRequestDto));

        verify(vrtVatRateValidationService).controlIsProductTypeUnique(vrtVatRate);

    }

    @Test
    void deleteVatRate() {

        VrtVatRate vrtVatRate = mock(VrtVatRate.class);

        when(vrtVatRateEntityService.getByIdWithControl(anyLong())).thenReturn(vrtVatRate);

        vrtVatRateService.deleteVatRate(anyLong());

        verify(vrtVatRateEntityService).getByIdWithControl(anyLong());
        verify(vrtVatRateEntityService).delete(any());
    }
}