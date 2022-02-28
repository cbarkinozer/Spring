package com.softtech.softtechspringboot.str.controller;



import com.softtech.softtechspringboot.str.dto.StrStreetDto;
import com.softtech.softtechspringboot.str.dto.StrStreetSaveRequestDto;
import com.softtech.softtechspringboot.str.service.StrStreetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/streets")
public class StrStreetController {
    private final StrStreetService strStreetService;

    //10. Street is savable
    @PostMapping
    public ResponseEntity save(@RequestBody StrStreetSaveRequestDto strStreetSaveRequestDto){
        StrStreetDto strStreetDto = strStreetService.save(strStreetSaveRequestDto);
        return ResponseEntity.ok(strStreetDto);
    }

    //11. Street name is updatable
    @PutMapping
    public ResponseEntity update(@RequestBody StrStreetDto strStreetDto){
        StrStreetDto strStreetDtoUpdated = strStreetService.update(strStreetDto);
        return ResponseEntity.ok(strStreetDtoUpdated);
    }
}
