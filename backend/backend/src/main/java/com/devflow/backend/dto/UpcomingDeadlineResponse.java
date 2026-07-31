package com.devflow.backend.dto;

import com.devflow.backend.entity.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpcomingDeadlineResponse {
    private Long id;
    private String title;
    private String projectName;
    private Priority priority;
    private LocalDate deadline;
}
