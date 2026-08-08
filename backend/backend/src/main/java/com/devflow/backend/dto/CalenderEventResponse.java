package com.devflow.backend.dto;

import com.devflow.backend.entity.Priority;
import com.devflow.backend.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalenderEventResponse {
    private Long id;
    private String title;
    private LocalDate date;
    private Status status;
    private Priority priority;
    private Long projectId;
    private String projectName;
}
