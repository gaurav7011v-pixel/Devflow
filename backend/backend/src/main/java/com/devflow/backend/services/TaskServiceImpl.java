package com.devflow.backend.services;

import com.devflow.backend.dto.CreateTaskRequest;
import com.devflow.backend.dto.TaskResponse;
import com.devflow.backend.dto.UpdateTaskRequest;
import com.devflow.backend.entity.Project;
import com.devflow.backend.entity.Task;
import com.devflow.backend.entity.User;
import com.devflow.backend.repository.ProjectRepository;
import com.devflow.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final CurrentUserService currentUserService;

    public TaskServiceImpl(TaskRepository taskRepository, CurrentUserService currentUserService) {
        this.taskRepository = taskRepository;
        this.currentUserService = currentUserService;
    }

    @Override
    public TaskResponse createTask(Long projectId,CreateTaskRequest taskRequest) {
        Task task=new Task();
        Project project = currentUserService.getProjectByIdAndOwner(projectId);
        task.setProject(project);
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        task.setPriority(taskRequest.getPriority());
        task.setDueDate(taskRequest.getDueDate());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask=taskRepository.save(task);
        return mapToTaskResponse(savedTask);
    }

    @Override
    public TaskResponse updateTask(Long id,UpdateTaskRequest taskRequest) {
        Task task=currentUserService.getTaskByIdAndOwner(id);
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        task.setPriority(taskRequest.getPriority());
        task.setDueDate(taskRequest.getDueDate());
        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask=taskRepository.save(task);
        return mapToTaskResponse(savedTask);
    }

    @Override
    public List<TaskResponse> getTasksByProject(Long projectId) {
        Project project = currentUserService.getProjectByIdAndOwner(projectId);

        return taskRepository.findByProject(project)
                .stream()
                .map(this::mapToTaskResponse)
                .toList();
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Task task=currentUserService.getTaskByIdAndOwner(id);
        return mapToTaskResponse(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task=currentUserService.getTaskByIdAndOwner(id);
        taskRepository.delete(task);
    }

    private TaskResponse mapToTaskResponse(Task task){
        TaskResponse taskResponse=new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setStatus(task.getStatus());
        taskResponse.setPriority(task.getPriority());
        taskResponse.setDueDate(task.getDueDate());
        return taskResponse;
    }

}
