package com.alerthub.loaderservice.controller;

import com.alerthub.loaderservice.config.GitHubImportProperties;
import com.alerthub.loaderservice.dto.GitHubFileRequest;
import com.alerthub.loaderservice.service.impl.GitHubCsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/api/import/github")
public class GitHubCsvImportController {

    @Autowired
    private GitHubCsvImportService importService;

    @Autowired
    private GitHubImportProperties config;

    //Import a local GitHub file manually
    @PostMapping
    public String importGitHubData(@RequestBody GitHubFileRequest request) {
        String fileName = request.getFileName();
        String fullPath = config.getBasePath() + fileName;
        File file = new File(fullPath);

        if (!file.exists()) {
            return "❌ File not found: " + file.getAbsolutePath();
        }

        importService.importCsv(file, fileName); // ✅ fixed to pass filename
        return "✅ Import started for file: " + file.getAbsolutePath();
    }

    // GitHub auto-import for all CSVs
    @PostMapping("/remote/all")
    public String importAllFromRemote() {
        importService.downloadAndImportAllCsvs();
        return "✅ Remote GitHub CSV import for all files started.";
    }
}
