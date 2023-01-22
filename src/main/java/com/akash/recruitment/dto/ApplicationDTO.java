package com.akash.recruitment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationDTO {

    private Long id;

    private Long fileId;

    private String email;

    private String fullName;

    private String mobileNo;

    private String status;

    private LocalDateTime insertedAt;

    private LocalDateTime updatedAt;

    public ApplicationDTO(Long fileId, String email, String fullName, String mobileNo) {
        this.fileId = fileId;
        this.email = email;
        this.fullName = fullName;
        this.mobileNo = mobileNo;
    }

    public ApplicationDTO() {
    }
}
