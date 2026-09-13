package com.Rightsy.demo.entity;

import com.Rightsy.demo.Dto.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    private Role role;
    private int totalScore;
    private Boolean emailVerified;
}
