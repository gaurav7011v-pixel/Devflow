package com.devflow.backend.services;

import com.devflow.backend.dto.ProjectInvitationResponse;
import com.devflow.backend.dto.ProjectMemberResponse;

import java.util.List;

public interface ProjectInvitationService {
    ProjectInvitationResponse sendInvitation(Long projectId,Long receiverId);
    List< ProjectInvitationResponse> getMyInvitations();
    ProjectInvitationResponse acceptInvitation(Long invitationId);
    ProjectInvitationResponse rejectInvitation(Long invitationId);
    List<ProjectMemberResponse> getProjectMembers(Long id);
}
