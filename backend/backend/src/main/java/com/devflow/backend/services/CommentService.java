package com.devflow.backend.services;

import com.devflow.backend.dto.CommentResponse;
import com.devflow.backend.dto.CreateCommentRequest;
import com.devflow.backend.dto.UpdateCommentRequest;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(Long taskId, CreateCommentRequest request);
    List<CommentResponse> getCommentByTask(Long taskId);
    CommentResponse editComment(Long commentId, UpdateCommentRequest request);
    void deleteComment(Long commentId);
}
