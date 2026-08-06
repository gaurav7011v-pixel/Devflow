package com.devflow.backend.repository;

import com.devflow.backend.entity.Project;
import com.devflow.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    Optional<Project> findByIdAndOwner(Long id, User owner);
    List<Project> findByOwner(User owner);
    long countByOwner(User owner);
}

