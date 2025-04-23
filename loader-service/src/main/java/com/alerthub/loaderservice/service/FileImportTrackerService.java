package com.alerthub.loaderservice.service;

import com.alerthub.loaderservice.entity.ImportedFile;
import com.alerthub.loaderservice.repository.ImportedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FileImportTrackerService {

    @Autowired
    private ImportedFileRepository repository;

    public boolean isFileAlreadyImported(String fileName, String platform) {
        return repository.existsByFileNameAndPlatform(fileName, platform);
    }

    public void markFileAsImported(String fileName, String platform) {
        if (!isFileAlreadyImported(fileName, platform)) {
            ImportedFile file = new ImportedFile();
            file.setFileName(fileName);
            file.setPlatform(platform);
            file.setImportedAt(LocalDateTime.now());
            repository.save(file);
        }
    }
}
