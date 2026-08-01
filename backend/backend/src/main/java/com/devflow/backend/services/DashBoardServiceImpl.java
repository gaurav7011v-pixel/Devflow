package com.devflow.backend.services;

import com.devflow.backend.dto.*;
import com.devflow.backend.entity.*;
import com.devflow.backend.repository.ActivityRepository;
import com.devflow.backend.repository.ProjectRepository;
import com.devflow.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DashBoardServiceImpl implements DashBoardService{
    private final ProjectRepository projectRepository;
    private final CurrentUserService currentUserService;
    private final TaskRepository taskRepository;
    private final ActivityRepository activityRepository;
    public DashBoardServiceImpl(ProjectRepository projectRepository, CurrentUserService currentUserService, TaskRepository taskRepository, ActivityRepository activityRepository) {
        this.projectRepository = projectRepository;
        this.currentUserService = currentUserService;
        this.taskRepository = taskRepository;
        this.activityRepository = activityRepository;
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

    @Override
    public List<UpcomingDeadlineResponse> getUpcomingDeadLines() {
        User owner=currentUserService.getCurrentUser();
        List<Task> tasks=taskRepository.findTop5ByProjectOwnerAndStatusNotAndDueDateGreaterThanEqualOrderByDueDateAsc(owner, Status.COMPLETED, LocalDate.now());
        return tasks.stream().map(this::mapToUpcomingDeadlineResponse).toList();
    }

    @Override
    public List<RecentActivityResponse> getRecentActivity() {
        User user=currentUserService.getCurrentUser();
        List<Activity> activities=activityRepository.findTop10ByUserOrderByCreatedAtDesc(user);
        return activities.stream().map(this::mapToRecentActivityResponse).toList();
    }

    private UpcomingDeadlineResponse mapToUpcomingDeadlineResponse(Task task) {
        UpcomingDeadlineResponse response = new UpcomingDeadlineResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setProjectName(task.getProject().getName());
        response.setPriority(task.getPriority());
        response.setDeadline(task.getDueDate());

        return response;
    }


    private RecentActivityResponse mapToRecentActivityResponse(Activity activity){
        RecentActivityResponse response=new RecentActivityResponse();
        response.setId(activity.getId());
        response.setDescription(activity.getDescription());
        response.setAction(activity.getAction());
        response.setCreatedAt(activity.getCreatedAt());

        return response;
    }


}


