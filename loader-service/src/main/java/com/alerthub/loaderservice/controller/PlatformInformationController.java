package com.alerthub.loaderservice.controller;

import com.alerthub.loaderservice.entity.PlatformInformation;
import com.alerthub.loaderservice.service.PlatformInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platform")
public class PlatformInformationController {

    private final PlatformInformationService service;

    @Autowired
    public PlatformInformationController(PlatformInformationService service) {
        this.service = service;
    }

    @PostMapping
    public PlatformInformation save(@RequestBody PlatformInformation info) {
        return service.savePlatformInfo(info);
    }

    @GetMapping("/all")
    public List<PlatformInformation> getAll() {
        return service.getAllPlatformInfo();
    }
}
