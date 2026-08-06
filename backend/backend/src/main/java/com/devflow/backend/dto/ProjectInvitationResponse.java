package com.devflow.backend.dto;

import com.devflow.backend.entity.InvitationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInvitationResponse {
    private Long id;
    private Long projectId;
    private String projectName;
    private Long senderId;
    private String senderName;
    private Long receiverId;
    private String receiverName;
    private InvitationStatus status;
}
