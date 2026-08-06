package com.devflow.backend.repository;

import com.devflow.backend.entity.Activity;
import com.devflow.backend.entity.Project;
import com.devflow.backend.entity.ProjectInvitation;
import com.devflow.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectInvitationRepository extends JpaRepository<ProjectInvitation,Long> {
    Optional<ProjectInvitation> findByProjectAndReceiver(Project project, User receiver);
    Optional<ProjectInvitation> findByIdAndReceiver(Long invitationId,User receiver);
    List<ProjectInvitation> findByReceiverOrderByCreatedAtDesc(User receiver);
}
