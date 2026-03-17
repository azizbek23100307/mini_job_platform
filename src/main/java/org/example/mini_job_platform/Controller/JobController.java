package org.example.mini_job_platform.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.mini_job_platform.payload.ApiResponse;
import org.example.mini_job_platform.payload.JobRequestDto;
import org.example.mini_job_platform.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;


    @GetMapping
    public ResponseEntity<ApiResponse> getAllJobs() {
        ApiResponse response = jobService.getAllJobs();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addJob(@Valid @RequestBody JobRequestDto dto) {
        ApiResponse response = jobService.addJob(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }



}
