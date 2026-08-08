package com.devflow.backend.controller;

import com.devflow.backend.dto.*;
import com.devflow.backend.entity.Priority;
import com.devflow.backend.entity.Status;
import com.devflow.backend.services.TaskService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping("/tasks/{taskId}/members/{userId}")
    public ResponseEntity<MemberResponse> assignMemberToTask(@PathVariable Long taskId, @PathVariable Long userId){
        MemberResponse response=taskService.assignMemberToTask(taskId,userId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/tasks/{taskId}/members/{userId}")
    public ResponseEntity<Void> removeMemberFromTask(@PathVariable Long taskId,@PathVariable Long userId){
        taskService.removeMemberFromTask(taskId,userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tasks/{taskId}/members")
    public ResponseEntity<List<MemberResponse>> getTaskMembers(@PathVariable Long taskId){
            List<MemberResponse> response =taskService.getTaskMembers(taskId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/tasks/search")
    public ResponseEntity<List<TaskResponse>> searchTasks(

            @RequestParam(required = false) Status status,

            @RequestParam(required = false) Priority priority,

            @RequestParam(required = false) Long projectId,

            @RequestParam(required = false) Long memberId,

            @RequestParam(required = false) Long labelId,

            @RequestParam(required = false) LocalDate dueDate,

            @RequestParam(required = false) String keyword
    ) {
        System.out.println("SEARCH API HIT");
        return ResponseEntity.ok(
                taskService.searchTasks(
                        status,
                        priority,
                        projectId,
                        memberId,
                        labelId,
                        dueDate,
                        keyword
                )
        );
    }

    @GetMapping("/calender/events")
    public ResponseEntity<List<CalenderEventResponse>> calenderEvents(){
        return ResponseEntity.ok(taskService.calenderEvents());
    }

    @GetMapping("/calender/event")
    public ResponseEntity<List<CalenderEventResponse>> calenderEventsBetween(@RequestParam LocalDate from,@RequestParam LocalDate to){
        return ResponseEntity.ok(taskService.calenderEventsBetween(from,to));
    }
}
