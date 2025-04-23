package com.example.processor.beans;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "platformInformation")
public class PlatformInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "project")
    private String project;

    @Column(name = "assignee")
    private String assignee;

    @Column(name = "label")
    private String label;

    @Column(name = "developer_id")
    private String developerId;

    @Column(name = "issue")
    private String issue;

    @Column(name = "environment")
    private String environment;

    @Column(name = "user_story")
    private String userStory;

    @Column(name = "point")
    private Integer point;

    @Column(name = "tag")
    private String tag;

    @Column(name = "worker_id")
    private String workerId;

    @Column(name = "task")
    private String task;

    @Column(name = "pr_env")
    private String prEnv;

    @Column(name = "day")
    private String day;

    @Column(name = "employeeID")
    private String employeeId;

    @Column(name = "task_number")
    private String taskNumber;

    @Column(name = "task_point")
    private Integer taskPoint;

    @Column(name = "sprint")
    private String sprint;

    @Column(name = "current_sprint")
    private String currentSprint;

    // No-arg constructor
    public PlatformInformation(String cpu, LocalDateTime now) {}

    // All-arg constructor
    public PlatformInformation(Long id, LocalDateTime timestamp, String ownerId, String project, String assignee,
                               String label, String developerId, String issue, String environment, String userStory,
                               Integer point, String tag, String workerId, String task, String prEnv, String day,
                               String employeeId, String taskNumber, Integer taskPoint, String sprint, String currentSprint) {
        this.id = id;
        this.timestamp = timestamp;
        this.ownerId = ownerId;
        this.project = project;
        this.assignee = assignee;
        this.label = label;
        this.developerId = developerId;
        this.issue = issue;
        this.environment = environment;
        this.userStory = userStory;
        this.point = point;
        this.tag = tag;
        this.workerId = workerId;
        this.task = task;
        this.prEnv = prEnv;
        this.day = day;
        this.employeeId = employeeId;
        this.taskNumber = taskNumber;
        this.taskPoint = taskPoint;
        this.sprint = sprint;
        this.currentSprint = currentSprint;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getUserStory() {
        return userStory;
    }

    public void setUserStory(String userStory) {
        this.userStory = userStory;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getPrEnv() {
        return prEnv;
    }

    public void setPrEnv(String prEnv) {
        this.prEnv = prEnv;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Integer getTaskPoint() {
        return taskPoint;
    }

    public void setTaskPoint(Integer taskPoint) {
        this.taskPoint = taskPoint;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public String getCurrentSprint() {
        return currentSprint;
    }

    public void setCurrentSprint(String currentSprint) {
        this.currentSprint = currentSprint;
    }
}
