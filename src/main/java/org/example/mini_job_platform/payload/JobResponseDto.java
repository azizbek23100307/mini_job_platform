package org.example.mini_job_platform.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JobResponseDto {

    private Integer Id;

    private String title;
    private String company;
    private Double salary;
    private String description;
}
