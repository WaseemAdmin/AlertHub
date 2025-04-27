package com.alerthub.evaluationservice.model;

import lombok.Data;

@Data
public class PlatformInformationDto {

    private int id;
    private String platform;        // GitHub, Jira, ClickUp
    private String assignee;         // Developer name
    private int developerId;         // developer ID
    private String project;          // Project name
    private String tag;              // Tag info
    private String label;            // Label info
    private String environment;      // dev/test/prod
    private String userStory;        // Description
    private int taskPoint;           // Story points
    private String sprint;           // Sprint name
    private int ownerId;             // Owner/manager ID
    private String taskNumber;       // Task number
}
