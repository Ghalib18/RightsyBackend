package com.Rightsy.demo.Dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateQuizRequestDto {
     @NotBlank
     private String topic;

     @Size(min=4,max=4)
     private List<@Valid QuizQuestionRequestDto> questions;
}
