package com.Rightsy.demo.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Setter
public class SrtQuestionResponseDto {

    private Long questionId;
    private String question;
    private String imageUrl;
    private List<String> prerequisites;
    private String optionA;
    private String noteA;
    private String optionB;
    private String noteB;
    private String optionC;
    private String noteC;
    private String optionD;
    private String noteD;
    private String answer;
}
