package com.alerthub.loaderservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "imported_files", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"file_name", "platform"})
})
@Data
public class ImportedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "platform", nullable = false)
    private String platform;

    @Column(name = "imported_at", nullable = false)
    private LocalDateTime importedAt = LocalDateTime.now();
}
