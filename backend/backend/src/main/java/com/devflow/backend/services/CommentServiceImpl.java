package com.devflow.backend.services;

import com.devflow.backend.dto.CommentResponse;
import com.devflow.backend.dto.CreateCommentRequest;
import com.devflow.backend.dto.UpdateCommentRequest;
import com.devflow.backend.entity.ActivityAction;
import com.devflow.backend.entity.Comment;
import com.devflow.backend.entity.Task;
import com.devflow.backend.entity.User;
import com.devflow.backend.exception.NoCommentFoundException;
import com.devflow.backend.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final CurrentUserService currentUserService;
    private final ActivityService activityService;

    public CommentServiceImpl(CommentRepository commentRepository, CurrentUserService currentUserService, ActivityService activityService) {
        this.commentRepository = commentRepository;
        this.currentUserService = currentUserService;
        this.activityService = activityService;
    }



    @Override
    public CommentResponse createComment(Long taskId, CreateCommentRequest request) {
        Comment comment=new Comment();
        Task task=currentUserService.getTaskByIdAndOwner(taskId);
        User currentUser = currentUserService.getCurrentUser();
        comment.setTask(task);
        comment.setAuthor(currentUser);
        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        Comment savedComment=commentRepository.save(comment);

        activityService.log(ActivityAction.COMMENT_ADDED,"Added comment to the"+ savedComment.getTask().getTitle());


        return mapToCommentResponse(savedComment);
    }



    @Override
    public List<CommentResponse> getCommentByTask(Long taskId) {
        Task task=currentUserService.getTaskByIdAndOwner(taskId);
        return commentRepository.findByTask(task)
                .stream()
                .map(this::mapToCommentResponse)
                .toList();
    }

    @Override
    public CommentResponse editComment(Long commentId, UpdateCommentRequest request) {
        Comment comment=currentUserService.getCommentByIdAndAuthor(commentId);
        comment.setContent(request.getContent());
        comment.setUpdatedAt(LocalDateTime.now());

        Comment savedComment=commentRepository.save(comment);

        activityService.log(ActivityAction.COMMENT_UPDATED,"Updated comment to the"+ savedComment.getTask().getTitle());


        return mapToCommentResponse(savedComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment=currentUserService.getCommentByIdAndAuthor(commentId);

        commentRepository.delete(comment);
    }
    private CommentResponse mapToCommentResponse(Comment comment) {
        CommentResponse commentResponse=new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setContent(comment.getContent());
        commentResponse.setAuthorName(comment.getAuthor().getName());
        commentResponse.setTaskId(comment.getTask().getId());
        commentResponse.setCreatedAt(comment.getCreatedAt());
        commentResponse.setUpdatedAt(comment.getUpdatedAt());
        return commentResponse;

    }
}
