package com.alerthub.loaderservice.repository;

import com.alerthub.loaderservice.entity.ImportedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportedFileRepository extends JpaRepository<ImportedFile, Long> 
{

    boolean existsByFileNameAndPlatform(String fileName, String platform);

}
