package org.example.mini_job_platform.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.mini_job_platform.payload.ApiResponse;
import org.example.mini_job_platform.payload.ApplicationRequestDto;
import org.example.mini_job_platform.service.ApplicationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> submitApplication(@Valid @ModelAttribute ApplicationRequestDto dto) {
        ApiResponse response = applicationService.submit(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllApplications() {
        ApiResponse response = applicationService.getAllApplications();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}