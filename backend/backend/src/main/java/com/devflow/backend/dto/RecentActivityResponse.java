package com.devflow.backend.dto;

import com.devflow.backend.entity.ActivityAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentActivityResponse{
    private Long id;
    private ActivityAction action;
    private String description;
    private LocalDateTime createdAt;
}
