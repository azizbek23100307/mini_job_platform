package org.example.mini_job_platform.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 120)
    private String title;

    @Column(nullable = false,length = 120)
    private String company;

    @Column(nullable = false)
    private Double salary;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;
}
