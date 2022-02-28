package com.softtech.softtechspringboot.add.controller;


import com.softtech.softtechspringboot.add.dto.AddAddressDto;
import com.softtech.softtechspringboot.add.dto.AddAddressSaveRequestDto;
import com.softtech.softtechspringboot.add.service.AddAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddAddressController {

    private final AddAddressService addAddressService;

    @GetMapping
    public ResponseEntity findAll(){
        List<AddAddressDto> addAddressDaoList=addAddressService.findAll();
        return ResponseEntity.ok(addAddressDaoList);
    }

    //15. find Address by Id.
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        AddAddressDto addAddressDto =addAddressService.findById(id);
        return ResponseEntity.ok(addAddressDto);
    }

    //13. Address is savable.
    @PostMapping
    public ResponseEntity save(@RequestBody AddAddressSaveRequestDto addAddressSaveRequestDto){
        AddAddressDto addAddressDto = addAddressService.save(addAddressSaveRequestDto);
        return ResponseEntity.ok(addAddressDto);
    }
    //14. Address is deletable .
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        addAddressService.delete(id);
        return ResponseEntity.ok(Void.TYPE);
    }
    @PutMapping
    public ResponseEntity update(@RequestBody AddAddressDto addAddressDto){
        AddAddressDto addAddressDtoUpdated = addAddressService.update(addAddressDto);
        return ResponseEntity.ok(addAddressDtoUpdated);
    }



}
