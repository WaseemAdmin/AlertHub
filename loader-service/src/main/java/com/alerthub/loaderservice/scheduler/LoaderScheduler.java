package com.alerthub.loaderservice.scheduler;

import com.alerthub.loaderservice.service.impl.ClickUpCsvImportService;
import com.alerthub.loaderservice.service.impl.GitHubCsvImportService;
import com.alerthub.loaderservice.service.impl.JiraCsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LoaderScheduler {

    @Autowired
    private ClickUpCsvImportService clickUpService;

    @Autowired
    private GitHubCsvImportService gitHubService;

    @Autowired
    private JiraCsvImportService jiraService;

    @Scheduled(fixedRate = 60 * 60 * 1000) // every 1 hour
    public void runHourlyCsvImports() {
        System.out.println("⏰ [Scheduler] Starting hourly CSV import...");

        clickUpService.downloadAndImportAllCsvs();
        gitHubService.downloadAndImportAllCsvs();
        jiraService.downloadAndImportAllCsvs();

        System.out.println("✅ [Scheduler] Hourly CSV import finished.");
    }
}
