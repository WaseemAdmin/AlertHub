package com.alerthub.evaluationservice.feign;

import com.alerthub.evaluationservice.model.PlatformInformationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "loader-service", url = "${alerthub.loader-service.url}")
public interface PlatformInformationClient {

    @GetMapping("/api/platform/all")
    List<PlatformInformationDto> getAllPlatformInformation();

}
