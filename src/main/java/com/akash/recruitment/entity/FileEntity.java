package com.akash.recruitment.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table
@Entity(name = "UPLOADED_FILE_INFO")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "TOTAL_APPLICATION")
    private Long totalApplication;

    @Column(name = "INSERTED_AT")
    private LocalDateTime insertedAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
