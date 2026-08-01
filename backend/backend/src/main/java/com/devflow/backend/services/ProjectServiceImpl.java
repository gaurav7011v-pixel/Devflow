package com.devflow.backend.services;

import com.devflow.backend.dto.CreateProjectRequest;
import com.devflow.backend.dto.ProjectResponse;
import com.devflow.backend.dto.RecentActivityResponse;
import com.devflow.backend.dto.UpdateProjectRequest;
import com.devflow.backend.entity.ActivityAction;
import com.devflow.backend.entity.Project;
import com.devflow.backend.entity.User;
import com.devflow.backend.repository.ActivityRepository;
import com.devflow.backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{
    private final CurrentUserService currentUserService;
    private final ProjectRepository projectRepository;
    private final ActivityService activityService;
    public ProjectServiceImpl(CurrentUserService currentUserService, ProjectRepository projectRepository, ActivityService activityService) {
        this.currentUserService = currentUserService;
        this.projectRepository = projectRepository;
        this.activityService = activityService;
    }

    @Override
    public ProjectResponse createProject(CreateProjectRequest projectRequest){
        Project project=new Project();
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        project.setStatus(projectRequest.getStatus());
        project.setPriority(projectRequest.getPriority());
        project.setCategory(projectRequest.getCategory());
        project.setStartDate(projectRequest.getStartDate());
        project.setDueDate(projectRequest.getDueDate());
        project.setGithubUrl(projectRequest.getGithubUrl());
        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());
        User owner=currentUserService.getCurrentUser();
        project.setOwner(owner);
        Project savedProject = projectRepository.save(project);

        activityService.log(
                ActivityAction.PROJECT_CREATED,
                "Created project " + savedProject.getName()
        );
        return mapToProjectResponse(savedProject);
    }

    @Override
    public List<ProjectResponse> getAllProjects() {
        User currentUser = currentUserService.getCurrentUser();
        return projectRepository.findByOwner(currentUser)
                .stream()
                .map(this::mapToProjectResponse)
                .toList();
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        Project project =currentUserService.getProjectByIdAndOwner(id);

        return mapToProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, UpdateProjectRequest projectRequest) {
        Project project =currentUserService.getProjectByIdAndOwner(id);
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        project.setStatus(projectRequest.getStatus());
        project.setPriority(projectRequest.getPriority());
        project.setStartDate(projectRequest.getStartDate());
        project.setDueDate(projectRequest.getDueDate());
        project.setGithubUrl(projectRequest.getGithubUrl());
        project.setUpdatedAt(LocalDateTime.now());

        Project updatedProject=projectRepository.save(project);

        activityService.log(
                ActivityAction.PROJECT_UPDATED,
                updatedProject.getName() +"updated"
        );
        return mapToProjectResponse(updatedProject);
    }

    @Override
    public void deleteProject(Long id) {
        Project project =currentUserService.getProjectByIdAndOwner(id);
        projectRepository.delete(project);

        activityService.log(ActivityAction.PROJECT_DELETED, project.getName()+" deleted");

    }

    private ProjectResponse mapToProjectResponse(Project project) {

        ProjectResponse response = new ProjectResponse();

        response.setId(project.getId());
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setStatus(project.getStatus());
        response.setPriority(project.getPriority());
        response.setGithubUrl(project.getGithubUrl());
        response.setStartDate(project.getStartDate());
        response.setDueDate(project.getDueDate());

        return response;
    }

}
