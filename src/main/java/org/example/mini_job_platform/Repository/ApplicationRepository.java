package org.example.mini_job_platform.Repository;


import org.example.mini_job_platform.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}