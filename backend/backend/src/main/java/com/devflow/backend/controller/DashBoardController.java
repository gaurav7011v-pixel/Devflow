package com.devflow.backend.controller;

import com.devflow.backend.dto.DashboardSummaryResponse;
import com.devflow.backend.dto.ProjectOverviewResponse;
import com.devflow.backend.dto.TaskSummaryResponse;
import com.devflow.backend.dto.UpcomingDeadlineResponse;
import com.devflow.backend.services.DashBoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DashBoardController {
   private final DashBoardService dashBoardService;

    public DashBoardController(DashBoardService dashBoardService) {
        this.dashBoardService = dashBoardService;
    }

    @GetMapping("/dashboard/summary")
    public ResponseEntity<DashboardSummaryResponse> getDashBoardSummary(){
        DashboardSummaryResponse response=dashBoardService.getDashboardSummary();
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/dashboard/projects")
    public ResponseEntity<List<ProjectOverviewResponse>> getProjectOverview(){
        List<ProjectOverviewResponse> response=dashBoardService.getProjectOverview();
        return  ResponseEntity.ok(response);
    }
    @GetMapping("/dashboard/task-summary")
    public ResponseEntity<TaskSummaryResponse> getTaskSummary(){
       TaskSummaryResponse response=dashBoardService.getTaskSummary();
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/dashboard/upcoming-deadlines")
    public ResponseEntity<List<UpcomingDeadlineResponse>> getUpcomingDeadlines(){
        List<UpcomingDeadlineResponse> response=dashBoardService.getUpcomingDeadLines();
        return  ResponseEntity.ok(response);
    }
}
