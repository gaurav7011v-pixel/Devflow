package com.devflow.backend.services;

import com.devflow.backend.dto.*;
import com.devflow.backend.entity.*;
import com.devflow.backend.repository.TaskRepository;
import com.devflow.backend.specification.TaskSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final CurrentUserService currentUserService;
    private final ActivityService activityService;

    public TaskServiceImpl(TaskRepository taskRepository, CurrentUserService currentUserService, ActivityService activityService) {
        this.taskRepository = taskRepository;
        this.currentUserService = currentUserService;
        this.activityService = activityService;
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

        activityService.log(ActivityAction.TASK_CREATED,"Created task "+ savedTask.getTitle());
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
        activityService.log(ActivityAction.TASK_UPDATED,"Updated task "+ savedTask.getTitle());

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

        activityService.log(ActivityAction.TASK_DELETED,currentUserService.getCurrentUser()+" deleted"+task.getTitle());

    }

    @Override
    public MemberResponse assignMemberToTask(Long taskId, Long userId) {
        Task task=currentUserService.getTaskByIdAndOwner(taskId);
        User user=currentUserService.getUserById(userId);

        if (!task.getMembers().contains(user)) {
            task.getMembers().add(user);
        }
        Task savedTask=taskRepository.save(task);
        activityService.log(ActivityAction.MEMBER_ASSIGNED,"Assigned "+ user.getName() +" to task "+ savedTask.getTitle());

        return mapToMemberResponse(user);
    }

    @Override
    public void removeMemberFromTask(Long taskId, Long userId) {
        Task task=currentUserService.getTaskByIdAndOwner(taskId);
        User user=currentUserService.getUserById(userId);

        task.getMembers().remove(user);
       Task savedTask= taskRepository.save(task);

        activityService.log(ActivityAction.MEMBER_REMOVED,user.getName()+" removed from "+ savedTask.getTitle());

    }

    @Override
    public List<MemberResponse> getTaskMembers(Long taskId) {
        Task task=currentUserService.getTaskByIdAndOwner(taskId);
        return task.getMembers().stream().map(this::mapToMemberResponse).toList();
    }

    @Override
    public List<TaskResponse> searchTasks( Status status,
                                           Priority priority,
                                           Long projectId,
                                           Long memberId,
                                           Long labelId,
                                           LocalDate dueDate,
                                           String keyword) {
        User owner = currentUserService.getCurrentUser();
        Specification<Task> spec =
                Specification.where(TaskSpecification.hasOwner(owner));

        if (status != null) {
            spec = spec.and(TaskSpecification.hasStatus(status));
        }

        if (priority != null) {
            spec = spec.and(TaskSpecification.hasPriority(priority));
        }

        if (projectId != null) {
            spec = spec.and(TaskSpecification.belongsToProject(projectId));
        }

        if (memberId != null) {
            User member=currentUserService.getUserById(memberId);
            spec = spec.and(TaskSpecification.hasMember(member));
        }

        if (labelId != null) {
            Label label=currentUserService.getLabelByByIdAndOwner(labelId);
            spec = spec.and(TaskSpecification.hasLabel(label));
        }

        if (dueDate != null) {
            spec = spec.and(TaskSpecification.hasDueDate(dueDate));
        }

        if (keyword != null && !keyword.isBlank()) {
            spec = spec.and(TaskSpecification.containsKeyword(keyword));
        }

        List<Task> tasks=taskRepository.findAll(spec);
        return tasks.stream().map(this::mapToTaskResponse).toList();
    }

    @Override
    public List<CalenderEventResponse> calenderEvents() {
        User owner=currentUserService.getCurrentUser();
        List<Task> tasks=taskRepository.findByProjectOwnerAndDueDateIsNotNull(owner);

        return tasks.stream().map(this::mapToCalenderEventResponse).toList();
    }

    @Override
    public List<CalenderEventResponse> calenderEventsBetween(LocalDate from, LocalDate to) {
        User user=currentUserService.getCurrentUser();
        List<Task> tasks=taskRepository.findByProjectOwnerAndDueDateBetween(user,from,to);
        return tasks.stream().map(this::mapToCalenderEventResponse).toList();
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
    private MemberResponse mapToMemberResponse(User user){
        MemberResponse response=new MemberResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }

    private CalenderEventResponse mapToCalenderEventResponse(Task task){
        CalenderEventResponse response=new CalenderEventResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDate(task.getDueDate());
        response.setStatus(task.getStatus());
        response.setPriority(task.getPriority());
        response.setProjectId(task.getProject().getId());
        response.setProjectName(task.getProject().getName());

        return response;
    }



}
