package com.devflow.backend.services;

import com.devflow.backend.entity.*;
import com.devflow.backend.exception.*;
import com.devflow.backend.repository.*;
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
    private final CheckListRepository checkListRepository;
    private final LabelRepository labelRepository;

    public CurrentUserService(UserRepository userRepository, ProjectRepository projectRepository, TaskRepository taskRepository, CommentRepository commentRepository, CheckListRepository checkListRepository, LabelRepository labelRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
        this.checkListRepository = checkListRepository;
        this.labelRepository = labelRepository;
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
    public CheckList getCheckListByIdAndOwner(Long checkListId){
        User currentUser=getCurrentUser();
        return checkListRepository.findByIdAndTaskProjectOwner(checkListId,currentUser).orElseThrow(
                ()->new CheckListItemNotFound("CheckList item not found!")
        );
    }
    public Label getLabelByByIdAndOwner(Long labelId){
        User currentUser=getCurrentUser();
        return labelRepository.findByIdAndOwner(labelId,currentUser).orElseThrow(
                ()->new LabelNotFoundException("Label not found!")
        );
    }
}
