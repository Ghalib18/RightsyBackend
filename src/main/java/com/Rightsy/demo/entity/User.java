package com.Rightsy.demo.entity;

import com.Rightsy.demo.Dto.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="users")
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


    public String getUsername() {
        return email;
    }
}
