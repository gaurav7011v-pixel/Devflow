package com.devflow.backend.controller;

import com.devflow.backend.dto.*;
import com.devflow.backend.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/tasks/{taskId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long taskId, @Valid @RequestBody CreateCommentRequest createCommentRequest){
        CommentResponse response=commentService.createComment(taskId,createCommentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/tasks/{taskId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentByTask(@PathVariable Long taskId){
        List<CommentResponse> response=commentService.getCommentByTask(taskId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> editComment(@PathVariable Long commentId ,@Valid @RequestBody UpdateCommentRequest updateCommentRequest){
        CommentResponse response=commentService.editComment(commentId,updateCommentRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
