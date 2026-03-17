package org.example.mini_job_platform.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequestDto {

    @NotBlank(message = "title bush bulmasligi kerak")
    private  String title;
    @NotBlank(message = "kompaniya nomi  bush bulmasligi kerak")
    private  String company;
    @NotBlank(message = "oylik  bush bulmasligi kerak")
    @Positive(message = "oylik musbat bo'lishi kerak")
    private Double salary;
    @NotBlank(message = " izoh  bush bulmasligi kerak")
    private String description;
}
