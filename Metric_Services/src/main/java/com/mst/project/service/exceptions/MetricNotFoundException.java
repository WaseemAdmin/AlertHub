package com.mst.project.service.exceptions;

public class MetricNotFoundException extends RuntimeException {
    public MetricNotFoundException(String message) {
        super(message);
    }
}
