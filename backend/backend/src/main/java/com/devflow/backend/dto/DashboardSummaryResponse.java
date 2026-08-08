package com.devflow.backend.dto;

import com.devflow.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardSummaryResponse {
    private Long totalProjects;
    private Long completedTasks;
    private Long teamMembers;
    private Long inProgressTasks;

}
