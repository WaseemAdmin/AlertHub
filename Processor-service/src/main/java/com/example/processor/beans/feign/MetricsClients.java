package com.example.processor.beans.feign;

import com.example.processor.beans.Metric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(name = "metrics-service", url = "http://localhost:8082")
public interface MetricsClients {
    @PostMapping("/metrics")
    void saveMetric(@RequestBody Metric metric);
    @GetMapping("/metrics")
    List<Metric> getAllMetrics();
}




