package com.Rightsy.demo.Dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginResponseDto {
    private String token;
    private Long id;
}
