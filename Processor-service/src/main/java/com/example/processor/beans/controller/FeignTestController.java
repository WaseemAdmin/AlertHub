package com.example.processor.beans.controller;

import com.example.processor.beans.PlatformInformation;
import com.example.processor.beans.feign.LoaderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class FeignTestController {

    @Autowired
    private LoaderClient loaderClient;

    @GetMapping("/loader/{ownerId}")
    public List<PlatformInformation> testLoader(@PathVariable String ownerId) {
        return loaderClient.getLoaderData(ownerId);
    }
}
