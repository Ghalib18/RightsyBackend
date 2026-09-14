package com.Rightsy.demo.Dto;


import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ForgetPasswordRequestDto {
    @Column(nullable = false)
    private String email;
}
