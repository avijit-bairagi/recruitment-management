package com.akash.recruitment.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table
@Entity(name = "UPLOADED_APPLICATION_INFO")
public class ApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FILE_ID")
    private Long fileId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "INSERTED_AT")
    private LocalDateTime insertedAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
