package com.devflow.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;
    private String githubUrl;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private User owner;

    @ManyToMany
    @JoinTable(
            name = "project_members",
            joinColumns=@JoinColumn(name="project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<User> projectMembers=new HashSet<>();
}
