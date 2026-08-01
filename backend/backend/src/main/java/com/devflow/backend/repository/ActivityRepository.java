package com.devflow.backend.repository;

import com.devflow.backend.entity.Activity;
import com.devflow.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Long> {
    List<Activity> findTop10ByUserOrderByCreatedAtDesc(User user);
}
