package org.example.mini_job_platform.mapper;


import org.example.mini_job_platform.entity.Application;
import org.example.mini_job_platform.payload.ApplicationResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public ApplicationResponseDto toDto(Application application) {
        return ApplicationResponseDto.builder()
                .id(application.getId())
                .fullName(application.getFullName())
                .phone(application.getPhone())
                .email(application.getEmail())
                .position(application.getPosition())
                .message(application.getMessage())
                .cvFileName(application.getCvFileName())
                .createdAt(application.getCreatedAt())
                .build();
    }
}