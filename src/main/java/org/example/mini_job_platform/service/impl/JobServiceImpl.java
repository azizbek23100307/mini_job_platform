package org.example.mini_job_platform.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.mini_job_platform.Repository.JobRepository;
import org.example.mini_job_platform.entity.Job;
import org.example.mini_job_platform.mapper.JobMapper;
import org.example.mini_job_platform.payload.ApiResponse;
import org.example.mini_job_platform.payload.JobRequestDto;
import org.example.mini_job_platform.payload.JobResponseDto;
import org.example.mini_job_platform.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private  final JobMapper jobMapper;
    private final JobRepository jobRepository;

    @Override
    public ApiResponse getAllJobs() {
        List<JobResponseDto> list = jobRepository.findAll().stream().map(jobMapper::toDto).toList();
        return new ApiResponse("Barcha ish elonlari olindi",true, HttpStatus.OK,list);

    }

    @Override
    public ApiResponse addJob(JobRequestDto dto) {
        Job entity = jobMapper.toEntity(dto);
        Job save = jobRepository.save(entity);

        return new ApiResponse("Ish e'loni muvaffqiyatli qushildi ",
                true,HttpStatus.CREATED,jobMapper.toDto(save));

    }
}
