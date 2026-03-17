package org.example.mini_job_platform.Repository;

import org.example.mini_job_platform.entity.Job;
import org.hibernate.validator.constraints.ru.INN;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
}
