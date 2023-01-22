package com.akash.recruitment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileDTO {

    private Long id;

    private String fileName;

    private Long totalApplication;

    private LocalDateTime insertedAt;

    private LocalDateTime updatedAt;
}
