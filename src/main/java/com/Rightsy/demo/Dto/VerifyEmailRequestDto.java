package com.Rightsy.demo.Dto;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyEmailRequestDto {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String code;
}
