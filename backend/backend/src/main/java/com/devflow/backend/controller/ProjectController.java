package com.devflow.backend.controller;

import com.devflow.backend.dto.*;
import com.devflow.backend.services.ProjectInvitationService;
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
    private final ProjectInvitationService projectInvitationService;

    public ProjectController(ProjectServiceImpl projectService, ProjectInvitationService projectInvitationService) {
        this.projectService = projectService;
        this.projectInvitationService = projectInvitationService;
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

    @PostMapping("/projects/{projectId}/invite/{userId}")
    public ResponseEntity<ProjectInvitationResponse> sendInvitation(@PathVariable Long projectId,@PathVariable Long userId){
        ProjectInvitationResponse response=projectInvitationService.sendInvitation(projectId,userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/invitations")
    public ResponseEntity<List<ProjectInvitationResponse>> getMyInvitations() {
        return ResponseEntity.ok(projectInvitationService.getMyInvitations());
    }

    @PostMapping("/invitations/{id}/accept")
    public ResponseEntity<ProjectInvitationResponse> acceptInvitation(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                projectInvitationService.acceptInvitation(id)
        );
    }

    @GetMapping("/projects/{id}/members")
    public ResponseEntity<List<ProjectMemberResponse>> getMembers(@PathVariable Long id){
        return ResponseEntity.ok(projectInvitationService.getProjectMembers(id));
    }

    @PostMapping("/invitations/{id}/reject")
    public ResponseEntity<ProjectInvitationResponse> rejectInvitation(
            @PathVariable Long id){

        return ResponseEntity.ok(
                projectInvitationService.rejectInvitation(id)
        );
    }

}
