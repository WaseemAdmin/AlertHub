package com.example.processor.beans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.processor.beans.PlatformInformation;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import java.time.LocalDateTime;



public interface PlatformInformationRepository extends JpaRepository<PlatformInformation, Long> {
    long countByLabelAndTimestampAfter(String label, LocalDateTime timestamp);
}

