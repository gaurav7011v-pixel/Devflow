package com.devflow.backend.repository;

import com.devflow.backend.entity.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> , JpaSpecificationExecutor<Task> {
    List<Task> findByProject(Project project);
    Optional<Task> findByIdAndProjectOwner(Long id, User owner);

    long countByProjectOwner(User owner);

    long countByProjectOwnerAndStatus(User owner, Status status);

    List<Task> findTop5ByProjectOwnerAndStatusNotAndDueDateGreaterThanEqualOrderByDueDateAsc(
            User owner, Status status
            , LocalDate today
            );

    List<Task> findByProjectOwnerAndDueDateIsNotNull(User owner);

    List<Task> findByProjectOwnerAndDueDateBetween(
            User owner,
            LocalDate from,
            LocalDate to
    );
}



