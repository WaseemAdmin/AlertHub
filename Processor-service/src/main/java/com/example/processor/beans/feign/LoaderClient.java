package com.example.processor.beans.feign;

import com.example.processor.beans.PlatformInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "loader-service", url = "http://localhost:8081")
public interface LoaderClient {
    @GetMapping("/loader/data/{ownerId}")
    List<PlatformInformation> getLoaderData(@PathVariable String ownerId);
}

