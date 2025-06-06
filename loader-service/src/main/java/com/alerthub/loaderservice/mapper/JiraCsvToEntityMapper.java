
package com.alerthub.loaderservice.mapper;

import com.alerthub.loaderservice.entity.PlatformInformation;
import com.alerthub.loaderservice.model.JiraCsvRecord;
import java.time.LocalDateTime;
	
public class JiraCsvToEntityMapper {

	    public static PlatformInformation map(JiraCsvRecord record) {
	        return PlatformInformation.builder()
	                .timestamp(LocalDateTime.now())
	                .ownerId(parseIntSafe(record.getManagerId()))
	                .project(record.getProjects())
	                .assignee(record.getAssignee())
	                .label(record.getLabel())
	                .developerId(parseIntSafe(record.getDeveloperId()))
	                .taskNumber(record.getTaskNumber())
	                .environment(record.getEnvironment())
	                .userStory(record.getUserStory())
	                .taskPoint(parseIntSafe(record.getPoint()))
	                .sprint(record.getSprint())
	                .platform("Jira")
	                .build();
	    }

	    private static int parseIntSafe(String value) {
	        try {
	            return Integer.parseInt(value.trim());
	        } catch (Exception e) {
	            System.out.println("⚠️ Warning: failed to parse int from value: " + value);
	            return 0;
	        }
	    }
}
