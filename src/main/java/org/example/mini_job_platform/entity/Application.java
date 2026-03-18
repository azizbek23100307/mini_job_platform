package org.example.mini_job_platform.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String fullName;

    @Column(nullable = false, length = 30)
    private String phone;

    @Column(nullable = false, length = 120)
    private String email;

    @Column(nullable = false, length = 120)
    private String position;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "cv_file_name")
    private String cvFileName;

    @Column(name = "cv_file_path")
    private String cvFilePath;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
