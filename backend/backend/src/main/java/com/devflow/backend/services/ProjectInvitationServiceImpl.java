package com.devflow.backend.services;

import com.devflow.backend.dto.ProjectInvitationResponse;
import com.devflow.backend.dto.ProjectMemberResponse;
import com.devflow.backend.entity.*;
import com.devflow.backend.exception.InvitationAlreadyExistsException;
import com.devflow.backend.exception.InvitationAlreadyProcessedException;
import com.devflow.backend.repository.ProjectInvitationRepository;
import com.devflow.backend.repository.ProjectRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectInvitationServiceImpl implements ProjectInvitationService{
    private final CurrentUserService currentUserService;
    private final ProjectInvitationRepository invitationRepository;
    private final ProjectRepository projectRepository;
    private final ActivityService activityService;

    public ProjectInvitationServiceImpl(CurrentUserService currentUserService, ProjectInvitationRepository invitationRepository, ProjectRepository projectRepository, ActivityService activityService) {
        this.currentUserService = currentUserService;
        this.invitationRepository = invitationRepository;
        this.projectRepository = projectRepository;
        this.activityService = activityService;
    }

    @Override
    public ProjectInvitationResponse sendInvitation(Long projectId, Long receiverId) {
        User sender=currentUserService.getCurrentUser();
        Project project=currentUserService.getProjectByIdAndOwner(projectId);
        User receiver=currentUserService.getUserById(receiverId);
        if(sender.getId().equals(receiver.getId())){
            throw new IllegalArgumentException(
                    "You cannot invite yourself."
            );
        }
        if(invitationRepository.findByProjectAndReceiver(project,receiver).isPresent()){
            throw new InvitationAlreadyExistsException("Invitation already sent");
        }
        ProjectInvitation invitation=new ProjectInvitation();
        invitation.setProject(project);
        invitation.setSender(sender);
        invitation.setReceiver(receiver);
        invitation.setCreatedAt(LocalDateTime.now());
        invitation.setStatus(InvitationStatus.PENDING);

        ProjectInvitation savedInvitation = invitationRepository.save(invitation);

        return mapToProjectInvitationResponse(savedInvitation);
    }



    @Override
    public List<ProjectInvitationResponse> getMyInvitations() {
        User receiver=currentUserService.getCurrentUser();
        return invitationRepository.findByReceiverOrderByCreatedAtDesc(receiver)
                .stream()
                .map(this::mapToProjectInvitationResponse)
                .toList();
    }

    @Override
    public ProjectInvitationResponse acceptInvitation(Long invitationId) {
        ProjectInvitation invitation=currentUserService.getInvitationByIdAndReceiver(invitationId);
        if(invitation.getStatus()!=InvitationStatus.PENDING){
            throw new InvitationAlreadyProcessedException("Invitation already processed exception");
        }
        Project project=invitation.getProject();

        project.getProjectMembers().add(invitation.getReceiver());

        invitation.setStatus(InvitationStatus.ACCEPTED);

        projectRepository.save(project);

        ProjectInvitation savedInvitation=invitationRepository.save(invitation);

        activityService.log(
                ActivityAction.MEMBER_ASSIGNED,
                invitation.getReceiver().getName()
                        + " joined project "
                        + project.getName()
        );
        return mapToProjectInvitationResponse(savedInvitation);
    }

    @Override
    public ProjectInvitationResponse rejectInvitation(Long invitationId) {
        ProjectInvitation invitation =
                currentUserService.getInvitationByIdAndReceiver(invitationId);

        if(invitation.getStatus()!=InvitationStatus.PENDING){
            throw new InvitationAlreadyProcessedException("Invitation already processed");
        }

        invitation.setStatus(InvitationStatus.REJECTED);

        ProjectInvitation savedInvitation =
                invitationRepository.save(invitation);

        return mapToProjectInvitationResponse(savedInvitation);

    }
    private ProjectInvitationResponse mapToProjectInvitationResponse(ProjectInvitation invitation) {
        ProjectInvitationResponse response=new ProjectInvitationResponse();
        response.setId(invitation.getId());
        response.setProjectId(invitation.getProject().getId());
        response.setProjectName(invitation.getProject().getName());
        response.setSenderId(invitation.getSender().getId());
        response.setSenderName(invitation.getSender().getName());
        response.setReceiverId(invitation.getReceiver().getId());
        response.setReceiverName(invitation.getReceiver().getName());
        response.setStatus(invitation.getStatus());

        return response;
    }

    @Override
    public List<ProjectMemberResponse> getProjectMembers(Long id) {
        Project project=currentUserService.getProjectByIdAndOwner(id);
        return project.getProjectMembers().stream().map(this::mapToProjectMemberResponse).toList();
    }

    private ProjectMemberResponse mapToProjectMemberResponse(User members) {

        ProjectMemberResponse response=new ProjectMemberResponse();
        response.setId(members.getId());
        response.setName(members.getName());

        return response;
    }
}
