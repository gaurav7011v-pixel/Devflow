package com.devflow.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSummaryResponse {
    private Long todo;
    private Long pending;
    private Long completed;
    private Long inProgress;
    private Long blocked;
    private Integer completionRate;
}
