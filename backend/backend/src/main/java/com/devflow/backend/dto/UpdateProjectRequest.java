package com.devflow.backend.dto;

import com.devflow.backend.entity.Category;
import com.devflow.backend.entity.Priority;
import com.devflow.backend.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProjectRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private Status status;
    @NotNull
    private Priority priority;
    @NotNull
    private Category category;
    private String githubUrl;
    private LocalDate startDate;
    private LocalDate dueDate;
}
