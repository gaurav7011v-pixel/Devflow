package com.devflow.backend.services;

import com.devflow.backend.entity.Comment;
import com.devflow.backend.entity.Project;
import com.devflow.backend.entity.Task;
import com.devflow.backend.entity.User;
import com.devflow.backend.exception.NoCommentFoundException;
import com.devflow.backend.exception.ProjectNotFoundException;
import com.devflow.backend.exception.TaskNotFoundException;
import com.devflow.backend.repository.CommentRepository;
import com.devflow.backend.repository.ProjectRepository;
import com.devflow.backend.repository.TaskRepository;
import com.devflow.backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    public CurrentUserService(UserRepository userRepository, ProjectRepository projectRepository, TaskRepository taskRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
    }

    public User getCurrentUser(){
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       String email=authentication.getName();
       return userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public Project getProjectByIdAndOwner(Long projectId){
        User currentUser=getCurrentUser();

        return projectRepository.findByIdAndOwner(projectId,currentUser).orElseThrow(
                () ->
                        new ProjectNotFoundException("Project not found")
        );
    }
    public Task getTaskByIdAndOwner(Long taskId){
        User currentUser=getCurrentUser();

        return taskRepository.findByIdAndProjectOwner(taskId,currentUser).orElseThrow(
                () ->
                        new TaskNotFoundException("Task not found")
        );
    }

    public Comment getCommentByIdAndAuthor(Long commentId){
        User currentUser=getCurrentUser();

        return commentRepository.findByIdAndAuthor(commentId,currentUser)
                .orElseThrow(()->new NoCommentFoundException("No comment found"));
    }
}
