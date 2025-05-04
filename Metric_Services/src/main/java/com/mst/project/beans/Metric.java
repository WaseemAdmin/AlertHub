package com.mst.project.beans;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name="user_id", nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Label label;

    @Column(nullable = false)
    private Integer threshold;

    @Column(nullable = false)
    private Integer timeFrameHours;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
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
