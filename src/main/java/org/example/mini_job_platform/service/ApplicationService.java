package org.example.mini_job_platform.service;

import org.example.mini_job_platform.payload.ApiResponse;
import org.example.mini_job_platform.payload.ApplicationRequestDto;

public interface ApplicationService {
    ApiResponse submit(ApplicationRequestDto dto);
    ApiResponse getAllApplications();
}
