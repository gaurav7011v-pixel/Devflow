package com.devflow.backend.dto;

import com.devflow.backend.entity.Category;
import com.devflow.backend.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectOverviewResponse {
    private Long id;
    private String projectName;
    private Integer progress;
    private Category category;
    private Status status;
}
