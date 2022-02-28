package com.softtech.softtechspringboot.dst.controller;

import com.softtech.softtechspringboot.dst.dto.DstDistrictDto;
import com.softtech.softtechspringboot.dst.dto.DstDistrictSaveRequestDto;
import com.softtech.softtechspringboot.dst.service.DstDistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/districts")
@RequiredArgsConstructor
public class DstDistrictController {
    private final DstDistrictService dstDistrictService;

    //5. District is savable
    @PostMapping
    public ResponseEntity save(@RequestBody DstDistrictSaveRequestDto dstDistrictSaveRequestDto){
        DstDistrictDto dstDistrictDto = dstDistrictService.save(dstDistrictSaveRequestDto);
        return ResponseEntity.ok(dstDistrictDto);
    }
    //6. Query districts that belongs to a city
    @GetMapping("/{cityId}")
    public ResponseEntity findByDistrictId(@PathVariable Long cityId){
        DstDistrictDto dstDistrictDto =dstDistrictService.findByCityId(cityId);
        return ResponseEntity.ok(dstDistrictDto);
    }
}
