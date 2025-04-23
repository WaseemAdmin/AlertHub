package com.alerthub.loaderservice.controller;

import com.alerthub.loaderservice.config.ClickUpImportProperties;
import com.alerthub.loaderservice.dto.ClickUpFileRequest;
import com.alerthub.loaderservice.service.impl.ClickUpCsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/api/import/clickup")
public class ClickUpCsvImportController {

    @Autowired
    private ClickUpCsvImportService importService;

    @Autowired
    private ClickUpImportProperties config;

    // Import from local CSV
    @PostMapping
    public String importClickUpData(@RequestBody ClickUpFileRequest request) {
        String fileName = request.getFileName();
        String fullPath = config.getBasePath() + fileName;
        File file = new File(fullPath);

        if (!file.exists()) {
            return "❌ File not found: " + file.getAbsolutePath();
        }

        importService.importCsv(file, fileName);  // ✅ updated to pass fileName
        return "✅ Import started for file: " + file.getAbsolutePath();
    }

    // Import all remote CSVs from GitHub
    @PostMapping("/remote/all")
    public String importRemoteClickUpData() {
        importService.downloadAndImportAllCsvs();
        return "✅ Remote ClickUp CSV import started.";
    }
}
