package org.example.mini_job_platform.payload;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponseDto {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String position;
    private String message;
    private String cvFileName;
    private LocalDateTime createdAt;
}
