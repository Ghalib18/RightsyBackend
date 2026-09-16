package com.Rightsy.demo.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetDto {

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String newPassword;
}
