package com.devflow.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSummaryResponse {
    private Long todo;        // 1st
    private Long completed;   // 2nd
    private Long inProgress;  // 3rd
    private Long pending;     // 4th
    private Long blocked;
}
