package com.devflow.backend.services;

import com.devflow.backend.dto.*;


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

}
