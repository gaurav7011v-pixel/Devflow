package com.devflow.backend.services;

import com.devflow.backend.dto.DashboardSummaryResponse;
import com.devflow.backend.dto.ProjectOverviewResponse;
import com.devflow.backend.dto.TaskSummaryResponse;
import com.devflow.backend.dto.UpcomingDeadlineResponse;

import java.util.List;

public interface DashBoardService {
    DashboardSummaryResponse getDashboardSummary();
    List<ProjectOverviewResponse> getProjectOverview();
    TaskSummaryResponse getTaskSummary();
    List<UpcomingDeadlineResponse> getUpcomingDeadLines();

}
