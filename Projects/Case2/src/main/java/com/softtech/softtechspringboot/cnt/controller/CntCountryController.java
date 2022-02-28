package com.softtech.softtechspringboot.cnt.controller;

import com.softtech.softtechspringboot.cnt.dto.CntCountryDto;
import com.softtech.softtechspringboot.cnt.dto.CntCountrySaveRequestDto;
import com.softtech.softtechspringboot.cnt.service.CntCountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
public class CntCountryController {
    private final CntCountryService cntCountryService;

    //1. Save Country
    @PostMapping
    public ResponseEntity save(@RequestBody CntCountrySaveRequestDto cntCountrySaveRequestDto){
        CntCountryDto cntCountryDto = cntCountryService.save(cntCountrySaveRequestDto);
        return ResponseEntity.ok(cntCountryDto);
    }
    //2. Query country by country code
    @GetMapping("/{countryCode}")
    public ResponseEntity findByCountryCode(@PathVariable String countryCode){
        CntCountryDto cntCountryDto =cntCountryService.findByCountryCode(countryCode);
        return ResponseEntity.ok(cntCountryDto);
    }

}
