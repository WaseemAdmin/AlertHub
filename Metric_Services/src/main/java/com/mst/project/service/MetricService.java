package com.mst.project.service;

import com.mst.project.beans.Label;
import com.mst.project.beans.Metric;
import com.mst.project.service.exceptions.MetricNotFoundException;

import java.util.List;
import java.util.UUID;

public interface MetricService {
    public List<Metric> GetAllMetric();
    public Metric getMetricById(UUID id) throws MetricNotFoundException;
    public List<Metric> GetAllMetricByUserId(Integer userId);
    public List<Metric> GetAllMetricByTimeFrameHours(Integer timeFrameHours);
    public List<Metric> findByLabel(Label label);
    public Metric CreateMetric(Metric metric);
    public Metric updateMetric(Metric metric) throws MetricNotFoundException;
    public Metric deleteMetric(UUID id) throws MetricNotFoundException;

}
