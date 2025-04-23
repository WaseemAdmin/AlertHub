package com.example.processor.beans;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Entity
public class ActionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ownerId;
    private String name;
    private String runOnDay; // "Sunday", "Monday", ..., "All"
    private String message;
    private boolean isEnabled;
    private boolean isDeleted;
    private LocalTime runOnTime;
    private LocalDateTime lastUpdate;
    private LocalDateTime lastRun;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "action_entity_id") // Make sure this matches your DB schema
    private List<Metric> metricConditions;
    public void setMetricConditions(List<Metric> metricConditions) {
        this.metricConditions = metricConditions;
    }

    private String sendTo;
    private LocalDateTime createDate;
    public enum ActionType {
        SMS, EMAIL
    }
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    @ElementCollection
    @Column(name = "metric_conditions")
    private List<String> condition;
    private MetricLabel label;



    public ActionEntity() {
    }

    public ActionEntity(Long id, String ownerId, String name, LocalDateTime createDate,
                        String sendto,List<String> condition,MetricLabel label,List<Metric> metricConditions
                        ,LocalTime runOnTime, String runOnDay, String message, boolean isEnabled,
                        boolean isDeleted, LocalDateTime lastUpdate, LocalDateTime lastRun) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.createDate = createDate;
        this.condition = condition;
        this.actionType = actionType;
        this.runOnTime = runOnTime;
        this.runOnDay = runOnDay;
        this.sendTo=sendto;
        this.message = message;
        this.isEnabled = isEnabled;
        this.isDeleted = isDeleted;
        this.lastUpdate = lastUpdate;
        this.lastRun = lastRun;
        this.label=label;
        this.metricConditions=metricConditions;
    }

    public Long getId() {
        return id;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTo() {
        return sendTo;
    }

    public List<Metric> getMetricConditions() {
        return metricConditions;
    }

    public void setTo(String to) {
        this.sendTo = sendTo;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public List<String> getCondition() {
        return condition;
    }

    public void setCondition(List<String> condition) {
        this.condition = condition;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public LocalTime getRunOnTime() {
        return runOnTime;
    }

    public String getSendTo() {
        return sendTo;
    }

    public MetricLabel getLabel() {
        return label;
    }

    public void setLabel(MetricLabel label) {
        this.label = label;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public LocalDateTime getLastRun() {
        return lastRun;
    }

    public void setLastRun(LocalDateTime lastRun) {
        this.lastRun = lastRun;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setRunOnTime(LocalTime runOnTime) {
        this.runOnTime = runOnTime;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRunOnDay() {
        return runOnDay;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRunOnDay(String runOnDay) {
        this.runOnDay = runOnDay;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}