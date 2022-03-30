package com.softtech.graduationproject.app.vrt.controller;

import com.softtech.graduationproject.app.gen.dto.RestResponse;
import com.softtech.graduationproject.app.usr.dto.UsrUserSaveRequestDto;
import com.softtech.graduationproject.app.vrt.dto.VrtVatRateDto;
import com.softtech.graduationproject.app.vrt.dto.VrtVatRateSaveRequestDto;
import com.softtech.graduationproject.app.vrt.dto.VrtVatRateUpdateRequestDto;
import com.softtech.graduationproject.app.vrt.service.VrtVatRateService;
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
@RequestMapping("api/v1/vat-rates")
public class VrtVatRateController {

    private final VrtVatRateService vrtVatRateService;

    @Operation(
            tags = "VAT Rate Controller",
            summary = "All VAT rates",
            description = "Gets all VAT rates."
    )
    @GetMapping
    public ResponseEntity<RestResponse<List<VrtVatRateDto>>> findAllVatRates(){

        List<VrtVatRateDto> vrtVatRateDtoList = vrtVatRateService.findAllVatRates();

        return ResponseEntity.ok(RestResponse.of(vrtVatRateDtoList));
    }


    @Operation(
            tags="VAT Rate Controller",
            summary = "Save a VAT rate",
            description = "Saves a new VAT rate"
    )
    @PostMapping("/save-vat-rate")
    public ResponseEntity<RestResponse<MappingJacksonValue>> saveVatRate(VrtVatRateSaveRequestDto vrtVatRateSaveRequestDto){

        VrtVatRateDto vrtVatRateDto = vrtVatRateService.saveVatRate(vrtVatRateSaveRequestDto);

        MappingJacksonValue mappingJacksonValue = createLinksForSave(vrtVatRateDto);

        return ResponseEntity.ok(RestResponse.of(mappingJacksonValue));
    }

    private MappingJacksonValue createLinksForSave (VrtVatRateDto vrtVatRateDto){

        WebMvcLinkBuilder linkGet = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                        this.getClass()).findAllVatRates());

        WebMvcLinkBuilder linkDelete = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(
                        this.getClass()).deleteVatRate(vrtVatRateDto.getId()));

        EntityModel<VrtVatRateDto> entityModel = EntityModel.of(vrtVatRateDto);

        entityModel.add(linkGet.withRel("find-all"));
        entityModel.add(linkDelete.withRel("delete"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entityModel);

        return mappingJacksonValue;
    }


    @Operation(
            tags="VAT Rate Controller",
            summary = "Update a VAT Rate",
            description = "Updates a VAT RATE."
    )
    @PutMapping("/update-vat-rate")
    public ResponseEntity<RestResponse<VrtVatRateDto>> updateVatRate(VrtVatRateUpdateRequestDto vrtVatRateUpdateRequestDto){

        VrtVatRateDto vrtVatRateDto = vrtVatRateService.updateVatRate(vrtVatRateUpdateRequestDto);

        return ResponseEntity.ok(RestResponse.of(vrtVatRateDto));

    }


    @Operation(
            tags="VAT Rate Controller",
            summary = "Delete a VAT Rate",
            description = "Deletes a VAT RATE."
    )
    @DeleteMapping("/delete-vat-rate/{id}")
    public ResponseEntity<RestResponse<?>> deleteVatRate(@PathVariable Long id){

        vrtVatRateService.deleteVatRate(id);

        return ResponseEntity.ok(RestResponse.empty());
    }

}
