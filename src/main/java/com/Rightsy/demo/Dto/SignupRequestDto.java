package com.Rightsy.demo.Dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {
    private String name;
    private String email;
    private String password;
}
