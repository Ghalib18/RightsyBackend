package com.Rightsy.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizResponse {

    private Long quizId;
    private String topic;

    private List<QuizQuestionResponseDto> questions;
}
