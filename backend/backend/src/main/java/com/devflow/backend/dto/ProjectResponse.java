package com.devflow.backend.dto;

import com.devflow.backend.entity.Category;
import com.devflow.backend.entity.Priority;
import com.devflow.backend.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private Status status;
    private Priority priority;
    private Category category;
    private LocalDate startDate;
    private LocalDate dueDate;
    private String githubUrl;
}
