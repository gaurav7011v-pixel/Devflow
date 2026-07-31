package com.devflow.backend.repository;

import com.devflow.backend.entity.Project;
import com.devflow.backend.entity.Status;
import com.devflow.backend.entity.Task;
import com.devflow.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByProject(Project project);
    Optional<Task> findByIdAndProjectOwner(Long id, User owner);

    long countByProjectOwner(User owner);

    long countByProjectOwnerAndStatus(User owner, Status status);

    List<Task> findTop5ByProjectOwnerAndStatusNotAndDueDateGreaterThanEqualOrderByDueDateAsc(
            User owner, Status status
            , LocalDate today
            );
}

