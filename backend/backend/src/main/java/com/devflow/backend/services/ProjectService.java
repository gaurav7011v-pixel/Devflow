package com.devflow.backend.services;

import com.devflow.backend.dto.CreateProjectRequest;
import com.devflow.backend.dto.ProjectResponse;
import com.devflow.backend.dto.UpdateProjectRequest;

import java.util.List;

public interface ProjectService {
    ProjectResponse createProject(CreateProjectRequest request);
    List<ProjectResponse> getAllProjects();
    ProjectResponse getProjectById(Long id);
    ProjectResponse updateProject(Long id,UpdateProjectRequest request);
    void deleteProject(Long id);

}
