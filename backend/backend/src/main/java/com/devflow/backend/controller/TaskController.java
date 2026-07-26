package com.devflow.backend.controller;

import com.devflow.backend.dto.CreateTaskRequest;
import com.devflow.backend.dto.TaskResponse;
import com.devflow.backend.dto.UpdateProjectRequest;
import com.devflow.backend.dto.UpdateTaskRequest;
import com.devflow.backend.services.TaskService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/projects/{projectId}/tasks")
    public ResponseEntity<TaskResponse> createTask(
            @PathVariable Long projectId,
            @RequestBody @Valid CreateTaskRequest request){
            TaskResponse response=taskService.createTask(projectId,request);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/projects/{projectId}/tasks")
    public ResponseEntity<List<TaskResponse>> getTasksByProject(@PathVariable Long projectId){
        List<TaskResponse> response=taskService.getTasksByProject(projectId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskResponse> getTaskByID(@PathVariable Long taskId){
        TaskResponse response=taskService.getTaskById(taskId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long taskId,@Valid @RequestBody UpdateTaskRequest request){
        TaskResponse response=taskService.updateTask(taskId,request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId){
       taskService.deleteTask(taskId);

        return ResponseEntity.noContent().build();
    }

}
