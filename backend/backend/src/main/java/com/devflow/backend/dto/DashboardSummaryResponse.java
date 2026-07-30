package com.devflow.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardSummaryResponse {
    private Long totalProjects;
    private Long totalTasks;
    private Long completedTasks;
    private Long inProgressTasks;

}
