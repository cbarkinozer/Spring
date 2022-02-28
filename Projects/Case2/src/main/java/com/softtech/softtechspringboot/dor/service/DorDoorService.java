package com.softtech.softtechspringboot.dor.service;

import com.softtech.softtechspringboot.dor.service.entityservice.DorDoorEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DorDoorService {
    private final DorDoorEntityService dorDoorEntityService;

}
