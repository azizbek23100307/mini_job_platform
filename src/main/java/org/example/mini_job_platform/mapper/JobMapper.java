package org.example.mini_job_platform.mapper;

import org.example.mini_job_platform.entity.Job;
import org.example.mini_job_platform.payload.JobRequestDto;
import org.example.mini_job_platform.payload.JobResponseDto;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {


    public Job toEntity (JobRequestDto requestDto){
        return  Job.builder()
                .title(requestDto.getTitle())
                .company(requestDto.getCompany())
                .salary(requestDto.getSalary())
                .description(requestDto.getDescription())
                .build();
    }

    public JobResponseDto toDto(Job job){
        return JobResponseDto.builder()
                .Id(job.getId())
                .title(job.getTitle())
                .salary(job.getSalary())
                .description(job.getDescription())
                .build();
    }
}
