package com.mst.project.controller;

import com.mst.project.beans.Label;
import com.mst.project.beans.Metric;
import com.mst.project.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/metric")
public class MetricController {

    @Autowired
    private MetricService metricService;

    @GetMapping
    public List<Metric> getAllStudents() {
        return metricService.GetAllMetric();
    }

    @GetMapping("/{id}")
    public Metric getMetricById(@PathVariable UUID id) {
        return metricService.getMetricById(id);
    }

    @GetMapping("/search/user-id")
    public List<Metric> getAllMetricByUserId(@RequestParam Integer userId) {
        return metricService.GetAllMetricByUserId(userId);
    }

    @GetMapping("/search/time-frame-hours")
    public List<Metric> getAllMetricByTimeFrameHours(@RequestParam Integer timeFrameHours) {
        return metricService.GetAllMetricByTimeFrameHours(timeFrameHours);
    }

    @GetMapping("/find/label")
    public List<Metric> findByLabel(@RequestParam Label label) {
        return metricService.findByLabel(label);
    }

    @GetMapping("/create/metric")
    public Metric createMetric(@RequestParam Metric metric) {
        return metricService.CreateMetric(metric);
    }

    @GetMapping("/update/metric")
    public Metric updateMetric(@RequestParam Metric metric) {
        return metricService.updateMetric(metric);
    }

    @GetMapping("/delete/metric")
    public Metric deleteMetric(@RequestParam UUID id) {
        return metricService.deleteMetric(id);
    }
}
