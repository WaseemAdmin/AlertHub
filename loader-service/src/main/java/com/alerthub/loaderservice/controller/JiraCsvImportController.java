package com.alerthub.loaderservice.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alerthub.loaderservice.config.JiraImportProperties;
import com.alerthub.loaderservice.dto.JiraFileRequest;
import com.alerthub.loaderservice.service.impl.JiraCsvImportService;

@RestController
@RequestMapping("/api/import/jira")
public class JiraCsvImportController {

    @Autowired
    private JiraCsvImportService importService;

    @Autowired
    private JiraImportProperties config;

    // Manual file import
    @PostMapping
    public String importJiraData(@RequestBody JiraFileRequest request) {
        String fileName = request.getFileName();
        String fullPath = config.getBasePath() + fileName;
        File file = new File(fullPath);

        if (!file.exists()) {
            return "❌ File not found: " + file.getAbsolutePath();
        }

        importService.importCsv(file, fileName);  // ✅ Pass filename
        return "✅ Import started for file: " + file.getAbsolutePath();
    }

    // GitHub remote hourly import
    @PostMapping("/remote/all")
    public String importRemoteJiraData() {
        importService.downloadAndImportAllCsvs();
        return "✅ Remote Jira CSV import started.";
    }
}
