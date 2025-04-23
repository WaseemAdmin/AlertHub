package com.alerthub.loaderservice.service.impl;

import com.alerthub.loaderservice.loader.ClickUpCsvLoader;
import com.alerthub.loaderservice.mapper.ClickUpCsvToEntityMapper;
import com.alerthub.loaderservice.model.ClickUpCsvRecord;
import com.alerthub.loaderservice.entity.PlatformInformation;
import com.alerthub.loaderservice.repository.PlatformInformationRepository;
import com.alerthub.loaderservice.service.FileImportTrackerService;
import com.alerthub.loaderservice.util.RemoteCsvDownloader;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;

@Service
public class ClickUpCsvImportService {

    @Autowired
    private ClickUpCsvLoader csvLoader;

    @Autowired
    private PlatformInformationRepository repository;
    
    @Autowired
    private FileImportTrackerService tracker;
    
    @Value("${alerthub.import.clickup.api}")
    private String clickupRemoteBaseUrl;

    private static final String PLATFORM = "ClickUp";
    
    public void importCsv(File file, String fileName) {
        try {
        	if (tracker.isFileAlreadyImported(fileName, PLATFORM)) {
                System.out.println("⏩ Skipped (already imported): " + fileName);
                return;
            }
        	
            System.out.println("📂 Loading file: " + file.getAbsolutePath());

            List<ClickUpCsvRecord> records = csvLoader.load(file);
            System.out.println("🟡 CSV records parsed: " + records.size());

            List<PlatformInformation> entities = records.stream()
                    .map(ClickUpCsvToEntityMapper::map)
                    .toList();

            System.out.println("🟢 Entities ready to insert: " + entities.size());

            repository.saveAll(entities);
            tracker.markFileAsImported(fileName, PLATFORM);
            System.out.println("✅ Imported " + entities.size() + " ClickUp records.");
        } catch (Exception e) {
            System.err.println("❌ Failed to import ClickUp CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void downloadAndImportAllCsvs() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(clickupRemoteBaseUrl, JsonNode.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                JsonNode files = response.getBody();
                for (JsonNode file : files) {
                    String fileName = file.get("name").asText();
                    if (fileName.endsWith(".csv")) {
                        String downloadUrl = file.get("download_url").asText();
                        File tempFile = RemoteCsvDownloader.downloadCsv(downloadUrl);
                        importCsv(tempFile, fileName);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to import ClickUp CSVs from folder: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
