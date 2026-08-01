package com.devflow.backend.services;

import com.devflow.backend.dto.*;

import java.util.List;

public interface DashBoardService {
    DashboardSummaryResponse getDashboardSummary();
    List<ProjectOverviewResponse> getProjectOverview();
    TaskSummaryResponse getTaskSummary();
    List<UpcomingDeadlineResponse> getUpcomingDeadLines();
    List<RecentActivityResponse> getRecentActivity();

}
