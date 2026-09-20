package com.Rightsy.demo.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class SrtQuestionReqDto {
    @NotBlank
    private String question;
    @NotEmpty
    private List<String> prerequisites;
    @NotBlank
    private String optionA;
    @NotBlank
    private String noteA;
    @NotBlank
    private String optionB;
    @NotBlank
    private String noteB;
    @NotBlank
    private String optionC;
    @NotBlank
    private String noteC;
    @NotBlank
    private String optionD;
    @NotBlank
    private String noteD;
    @NotBlank
    private String answer;
}
