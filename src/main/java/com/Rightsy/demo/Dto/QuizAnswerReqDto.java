package com.Rightsy.demo.Dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class QuizAnswerReqDto {

    private Long quizId;
    private List<QuestionAnsReqDto> answers;
}
