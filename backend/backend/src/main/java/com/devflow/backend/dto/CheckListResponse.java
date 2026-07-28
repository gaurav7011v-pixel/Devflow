package com.devflow.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckListResponse {
    private Long id;
    private String title;
    private Boolean completed;
    private Long taskId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
