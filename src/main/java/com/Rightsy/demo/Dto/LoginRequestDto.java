package com.Rightsy.demo.Dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    private String email;
    private String password;
}
