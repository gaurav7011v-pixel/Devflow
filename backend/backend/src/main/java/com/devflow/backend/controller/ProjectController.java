package com.devflow.backend.controller;

import com.devflow.backend.dto.CreateProjectRequest;
import com.devflow.backend.dto.ProjectResponse;
import com.devflow.backend.dto.UpdateProjectRequest;
import com.devflow.backend.services.ProjectService;
import com.devflow.backend.services.ProjectServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/projects")
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody CreateProjectRequest createProjectRequest){
        ProjectResponse response=projectService.createProject(createProjectRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
     }
    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponse>> getAllProjects(){
        List<ProjectResponse> response=projectService.getAllProjects();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id){
        ProjectResponse response=projectService.getProjectById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id ,@Valid @RequestBody UpdateProjectRequest updateProjectRequest){
        ProjectResponse response=projectService.updateProject(id,updateProjectRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
