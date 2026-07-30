package com.devflow.backend.services;

import com.devflow.backend.dto.DashboardSummaryResponse;
import com.devflow.backend.dto.ProjectOverviewResponse;
import com.devflow.backend.dto.TaskSummaryResponse;
import com.devflow.backend.entity.Project;
import com.devflow.backend.entity.Status;
import com.devflow.backend.entity.User;
import com.devflow.backend.repository.ProjectRepository;
import com.devflow.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashBoardServiceImpl implements DashBoardService{
    private final ProjectRepository projectRepository;
    private final CurrentUserService currentUserService;
    private final TaskRepository taskRepository;
    public DashBoardServiceImpl(ProjectRepository projectRepository, CurrentUserService currentUserService, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.currentUserService = currentUserService;
        this.taskRepository = taskRepository;
    }

    @Override
    public DashboardSummaryResponse getDashboardSummary(){
        User currentUser=currentUserService.getCurrentUser();
        Long totalProjects=projectRepository.countByOwner(currentUser);
        Long totalTasks=taskRepository.countByProjectOwner(currentUser);
        Long completedTasks=taskRepository.countByProjectOwnerAndStatus(currentUser, Status.COMPLETED);
        Long inProgressTasks=taskRepository.countByProjectOwnerAndStatus(currentUser,Status.IN_PROGRESS);

        return new DashboardSummaryResponse(
                totalProjects,
                totalTasks,
                completedTasks,
                inProgressTasks
        );
    }

    @Override
    public List<ProjectOverviewResponse> getProjectOverview() {
        User currentUser=currentUserService.getCurrentUser();
        List<Project> projects=projectRepository.findByOwner(currentUser);
        return projects.stream().map(this::mapToProjectOverviewResponse).toList();
    }

    @Override
    public TaskSummaryResponse getTaskSummary() {
        User currentUser=currentUserService.getCurrentUser();
        Long todo=taskRepository.countByProjectOwnerAndStatus(currentUser,Status.TODO);
        Long completed=taskRepository.countByProjectOwnerAndStatus(currentUser,Status.COMPLETED);
        Long inProgress=taskRepository.countByProjectOwnerAndStatus(currentUser,Status.IN_PROGRESS);
        Long pending=taskRepository.countByProjectOwnerAndStatus(currentUser,Status.PENDING);
        Long blocked=taskRepository.countByProjectOwnerAndStatus(currentUser,Status.BLOCKED);

        long totalTasks = todo + inProgress +pending+ completed + blocked;

        int completionPercentage = 0;

        if (totalTasks > 0) {
            completionPercentage = (int) ((completed * 100) / totalTasks);
        }

        return new TaskSummaryResponse(
                todo,
                completed,
                inProgress,
                pending,
                blocked,
                completionPercentage
        ) ;
    }

    private ProjectOverviewResponse mapToProjectOverviewResponse(Project project) {
        User currentUser=currentUserService.getCurrentUser();
        Long totalTasks=taskRepository.countByProjectOwner(currentUser);
        Long completedTasks=taskRepository.countByProjectOwnerAndStatus(currentUser, Status.COMPLETED);

        int progress=0;
        if(totalTasks>0){
            progress = (int) ((completedTasks * 100) / totalTasks);
        }

        ProjectOverviewResponse response = new ProjectOverviewResponse();

        response.setId(project.getId());
        response.setProjectName(project.getName());
        response.setCategory(project.getCategory());
        response.setStatus(project.getStatus());
        response.setProgress(progress);

        return response;

    }




}


