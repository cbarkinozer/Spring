package com.softtech.softtechspringboot.prd.controller;

import com.softtech.softtechspringboot.gen.dto.RestResponse;
import com.softtech.softtechspringboot.prd.service.PrdProductService;
import com.softtech.softtechspringboot.prd.dto.PrdProductDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class PrdProductController {

    private final PrdProductService prdProductService;

    @Operation(summary="Get all products.")
    @GetMapping
    public ResponseEntity findAll(){
        List<PrdProductDto> prdProductDtoList = prdProductService.findAll();
        return ResponseEntity.ok(RestResponse.of(prdProductDtoList));
    }

    @Operation(summary="Get a product by id.")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        PrdProductDto prdProductDto = prdProductService.findById(id);
        return ResponseEntity.ok(RestResponse.of(prdProductDto));
    }

    @Operation(summary="Save a product.")
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody PrdProductDto prdProductDto){
        PrdProductDto prdProductDtoSaved = prdProductService.save(prdProductDto);
        return ResponseEntity.ok(RestResponse.of(prdProductDtoSaved));
    }

    @Operation(summary="Update the price of a product.")
    @PatchMapping("/update-price/{id}")
    public ResponseEntity updatePrice(@PathVariable Long id, @RequestParam BigDecimal price){
        PrdProductDto prdProductDto = prdProductService.updatePrice(id,price);
        return ResponseEntity.ok(RestResponse.of(prdProductDto));
    }

    @Operation(summary="Delete a product.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        prdProductService.delete(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

}
