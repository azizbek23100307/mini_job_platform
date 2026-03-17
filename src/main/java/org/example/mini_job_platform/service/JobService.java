package org.example.mini_job_platform.service;

import org.example.mini_job_platform.payload.ApiResponse;
import org.example.mini_job_platform.payload.JobRequestDto;

public interface JobService {

    ApiResponse getAllJobs();
    ApiResponse addJob(JobRequestDto dto);
}
