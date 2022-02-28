package com.softtech.softtechspringboot.ngh.controller;


import com.softtech.softtechspringboot.ngh.dto.NghNeighborhoodDto;
import com.softtech.softtechspringboot.ngh.dto.NghNeighborhoodSaveRequestDto;
import com.softtech.softtechspringboot.ngh.service.NghNeighborhoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/neighborhoods")
public class NghNeighborhoodController {
    private final NghNeighborhoodService nghNeighborhoodService;

    //7. Neighborhood is savable
    @PostMapping
    public ResponseEntity save(@RequestBody NghNeighborhoodSaveRequestDto nghNeighborhoodSaveRequestDto){
        NghNeighborhoodDto nghNeighborhoodDto = nghNeighborhoodService.save(nghNeighborhoodSaveRequestDto);
        return ResponseEntity.ok(nghNeighborhoodDto);
    }
    //8. Neighborhood name is updatable
    @PutMapping
    public ResponseEntity update(@RequestBody NghNeighborhoodDto nghNeighborhoodDto){
        NghNeighborhoodDto nghNeighborhoodDtoUpdated = nghNeighborhoodService.update(nghNeighborhoodDto);
        return ResponseEntity.ok(nghNeighborhoodDtoUpdated);
    }
    //9. Query neighborhoods that belongs to a district
    @GetMapping("/{districtId}")
    public ResponseEntity findByDistrictId(@PathVariable Long districtId){
        NghNeighborhoodDto nghNeighborhoodDto =nghNeighborhoodService.findByDistrictId(districtId);
        return ResponseEntity.ok(nghNeighborhoodDto);
    }


}
