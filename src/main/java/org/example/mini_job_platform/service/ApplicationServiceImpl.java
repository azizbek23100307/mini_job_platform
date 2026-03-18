package org.example.mini_job_platform.service;


import lombok.RequiredArgsConstructor;
import org.example.mini_job_platform.Repository.ApplicationRepository;
import org.example.mini_job_platform.entity.Application;
import org.example.mini_job_platform.mapper.ApplicationMapper;
import org.example.mini_job_platform.payload.ApiResponse;
import org.example.mini_job_platform.payload.ApplicationRequestDto;
import org.example.mini_job_platform.payload.ApplicationResponseDto;
import org.example.mini_job_platform.service.ApplicationService;
import org.example.mini_job_platform.service.FileStorageService;
import org.example.mini_job_platform.telegram.TelegramNotifierService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final FileStorageService fileStorageService;
    private final TelegramNotifierService telegramNotifierService;

    @Override
    public ApiResponse submit(ApplicationRequestDto dto) {
        String savedFilePath = null;
        String savedFileName = null;

        if (dto.getCvFile() != null && !dto.getCvFile().isEmpty()) {
            savedFilePath = fileStorageService.saveFile(dto.getCvFile());
            savedFileName = dto.getCvFile().getOriginalFilename();
        }

        Application application = Application.builder()
                .fullName(dto.getFullName().trim())
                .phone(dto.getPhone().trim())
                .email(dto.getEmail().trim())
                .position(dto.getPosition().trim())
                .message(dto.getMessage() != null ? dto.getMessage().trim() : null)
                .cvFileName(savedFileName)
                .cvFilePath(savedFilePath)
                .createdAt(LocalDateTime.now())
                .build();

        Application savedApplication = applicationRepository.save(application);

        String notifyText = """
                Yangi ariza keldi!

                Ism: %s
                Telefon: %s
                Email: %s
                Lavozim: %s
                Xabar: %s
                """.formatted(
                savedApplication.getFullName(),
                savedApplication.getPhone(),
                savedApplication.getEmail(),
                savedApplication.getPosition(),
                savedApplication.getMessage() != null ? savedApplication.getMessage() : "-"
        );

        telegramNotifierService.notifyNewApplication(notifyText);

        if (savedApplication.getCvFilePath() != null) {
            telegramNotifierService.sendApplicationFile(
                    savedApplication.getCvFilePath(),
                    "CV / Resume: " + savedApplication.getFullName()
            );
        }

        ApplicationResponseDto responseDto = applicationMapper.toDto(savedApplication);

        return new ApiResponse(
                "Ariza muvaffaqiyatli yuborildi",
                true,
                HttpStatus.CREATED,
                responseDto
        );
    }

    @Override
    public ApiResponse getAllApplications() {
        List<ApplicationResponseDto> applications = applicationRepository.findAll()
                .stream()
                .map(applicationMapper::toDto)
                .toList();

        return new ApiResponse(
                "Barcha arizalar olindi",
                true,
                HttpStatus.OK,
                applications
        );
    }
}
