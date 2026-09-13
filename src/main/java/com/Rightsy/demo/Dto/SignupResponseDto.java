package com.Rightsy.demo.Dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponseDto {
    private Long id;
    private String name;
    private String email;
    private int total;
}
