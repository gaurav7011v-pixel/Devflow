package com.devflow.backend.specification;

import com.devflow.backend.entity.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TaskSpecification {
    public static Specification<Task> hasStatus(Status status){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"),status));
    }

    public static Specification<Task> hasPriority(Priority priority){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("priority"),priority));
    }

    public static Specification<Task> hasOwner(User owner){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("project").get("owner"),owner);
    }

    public static Specification<Task> belongsToProject(Long projectId){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("project").get("id"),projectId);
    }

    public static Specification<Task> hasMember(User member){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isMember(member,root.get("members"));
    }

    public static Specification<Task> hasLabel(Label label){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isMember(label,root.get("labels"));
    }

    public static Specification<Task> containsKeyword(String keyword){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title"))
                                ,"%"+keyword+"%"
                        ),
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("description"))
                                ,"%"+keyword+"%")
                );
    }

    public static Specification<Task> hasDueDate(LocalDate dueDate){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("dueDate"),dueDate);
    }
}
