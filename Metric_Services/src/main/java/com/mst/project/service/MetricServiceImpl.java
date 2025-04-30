package com.mst.project.service;

import com.mst.project.beans.Label;
import com.mst.project.beans.Metric;
import com.mst.project.repository.MetricRepository;
import com.mst.project.service.exceptions.MetricNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public abstract class MetricServiceImpl implements MetricService {
    @Autowired
    private MetricRepository metricRepository;

    public List<Metric> GetAllMetric(){
        return metricRepository.findAll();
    }

    public Metric getMetricById(UUID id) throws MetricNotFoundException {
        return metricRepository.findById(id)
                .orElseThrow(() -> new MetricNotFoundException("Metric with ID " + id + " not found"));
    }

    public List<Metric> GetAllMetricByUserId(Integer userId){
        return metricRepository.findByUserId(userId);
    }
    public List<Metric> GetAllMetricByTimeFrameHours(Integer timeFrameHours){
        return metricRepository.findByTimeFrameHours(timeFrameHours);
    }

    public List<Metric> findByLabel(Label label){
        return metricRepository.findByLabel(label);
    }
    public Metric CreateMetric(Metric metric){
        return metricRepository.save(metric);
    }

    public Metric updateMetric(Metric metric) throws MetricNotFoundException{
        Metric existingMetric = metricRepository.findById(metric.getId())
                .orElseThrow(() -> new MetricNotFoundException("Metric with ID " + metric.getId() + " not found"));

        existingMetric.setName(metric.getName());
        existingMetric.setUserId(metric.getUserId());
        existingMetric.setLabel(metric.getLabel());
        existingMetric.setThreshold(metric.getThreshold());
        existingMetric.setTimeFrameHours(metric.getTimeFrameHours());

        return metricRepository.save(existingMetric);
    }

    public Metric deleteMetric(UUID id) throws MetricNotFoundException{
        Metric metric = metricRepository.findById(id)
                .orElseThrow(() -> new MetricNotFoundException("Metric with ID " + id + " not found"));

        metricRepository.delete(metric);
        return metric;
    }
}
