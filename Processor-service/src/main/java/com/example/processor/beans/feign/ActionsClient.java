package com.example.processor.beans.feign;
import com.example.processor.beans.ActionEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "actions-service", url = "http://localhost:8084")
public interface ActionsClient {
    @PostMapping("/actions/schedule")
    void scheduleAction(@RequestBody ActionEntity action);
}
