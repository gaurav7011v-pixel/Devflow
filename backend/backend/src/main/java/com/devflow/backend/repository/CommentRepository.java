package com.devflow.backend.repository;

import com.devflow.backend.entity.Comment;
import com.devflow.backend.entity.Task;
import com.devflow.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<Comment> findByIdAndAuthor(Long id, User Author);
    List<Comment> findByTask(Task task);
}
