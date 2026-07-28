package com.devflow.backend.repository;

import com.devflow.backend.entity.CheckList;
import com.devflow.backend.entity.Task;
import com.devflow.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CheckListRepository extends JpaRepository<CheckList,Long> {
    Optional<CheckList> findByIdAndTaskProjectOwner(Long id, User owner);
    List<CheckList> findByTask(Task task);
}
