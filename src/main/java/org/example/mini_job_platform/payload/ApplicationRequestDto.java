package org.example.mini_job_platform.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ApplicationRequestDto {

    @NotBlank(message = "Full name bo'sh bo'lmasligi kerak")
    private String fullName;

    @NotBlank(message = "Phone bo'sh bo'lmasligi kerak")
    private String phone;

    @NotBlank(message = "Email bo'sh bo'lmasligi kerak")
    @Email(message = "Email noto'g'ri formatda")
    private String email;

    @NotBlank(message = "Position bo'sh bo'lmasligi kerak")
    private String position;

    private String message;

    private MultipartFile cvFile;
}
