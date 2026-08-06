package com.devflow.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank
    @Size(min=8)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String profileImage;

    @ManyToMany(mappedBy = "members")
    private List<Task> assignedTasks=new ArrayList<>();

    @ManyToMany(mappedBy = "projectMembers")
    private List<Project> assignedProject=new ArrayList<>();

}
