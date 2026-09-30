package com.devflow.backend.dto;

import com.devflow.backend.entity.Priority;
import com.devflow.backend.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDate dueDate;

}
