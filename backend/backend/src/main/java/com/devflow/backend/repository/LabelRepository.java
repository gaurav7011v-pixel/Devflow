package com.devflow.backend.repository;

import com.devflow.backend.entity.Label;
import com.devflow.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LabelRepository extends JpaRepository<Label,Long> {
    Optional<Label> findByIdAndOwner(Long id, User owner);
    List<Label> findByOwner(User user);
}
