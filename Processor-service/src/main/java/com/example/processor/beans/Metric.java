package com.example.processor.beans;

import jakarta.persistence.*;

@Entity
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer userId;
    private String name;

    @Enumerated(EnumType.STRING)
    private MetricLabel label;

    private Integer threshold;
    private Integer timeFrameHours;
    public Metric(MetricLabel label, int threshold, int timeFrameHours) {
        this.label = label;
        this.threshold = threshold;
        this.timeFrameHours = timeFrameHours;
    }

    public enum Label {
        BUG, DOCUMENTATION, DUPLICATE, ENHANCEMENT, GOOD_FIRST_ISSUE,
        HELP_WANTED, INVALID, QUESTION, WONTFIX
    }

    // No-arg constructor
    public Metric(long l, String cpu, int i, int i1) {}

    // All-args constructor
    public Metric(Long id, Integer userId, String name, MetricLabel label, Integer threshold, Integer timeFrameHours) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.label = label;
        this.threshold = threshold;
        this.timeFrameHours = timeFrameHours;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetricLabel getLabel() {
        return label;
    }

    public void setLabel(MetricLabel label) {
        this.label = label;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getTimeFrameHours() {
        return timeFrameHours;
    }

    public void setTimeFrameHours(Integer timeFrameHours) {
        this.timeFrameHours = timeFrameHours;
    }
}
