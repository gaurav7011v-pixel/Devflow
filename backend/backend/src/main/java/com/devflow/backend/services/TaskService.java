package com.devflow.backend.services;

import com.devflow.backend.dto.*;
import com.devflow.backend.entity.Priority;
import com.devflow.backend.entity.Status;
import com.devflow.backend.entity.Task;


import java.time.LocalDate;
import java.util.List;

public interface TaskService {
     TaskResponse createTask(Long id, CreateTaskRequest taskRequest);

     TaskResponse updateTask(Long id, UpdateTaskRequest taskRequest);

     List<TaskResponse> getTasksByProject(Long projectId);

     TaskResponse getTaskById(Long Id);

     void deleteTask(Long id);

     MemberResponse assignMemberToTask(Long taskId, Long userId);

     void removeMemberFromTask(Long taskId, Long userId);

     List<MemberResponse> getTaskMembers(Long taskId);

     List<TaskResponse> searchTasks( Status status,
                                     Priority priority,
                                     Long projectId,
                                     Long memberId,
                                     Long labelId,
                                     LocalDate dueDate,
                                     String keyword);


     List<CalenderEventResponse> calenderEvents();
     List<CalenderEventResponse> calenderEventsBetween(LocalDate from,LocalDate to);

}
