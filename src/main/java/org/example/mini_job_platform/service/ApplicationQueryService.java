package org.example.mini_job_platform.service;

import lombok.RequiredArgsConstructor;
import org.example.mini_job_platform.Repository.ApplicationRepository;
import org.example.mini_job_platform.entity.Application;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationQueryService {

    private final ApplicationRepository applicationRepository;

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }
}