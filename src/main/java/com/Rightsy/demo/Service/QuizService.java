package com.Rightsy.demo.Service;

import com.Rightsy.demo.Dto.ApiResponseDto;
import com.Rightsy.demo.Dto.CreateQuizRequestDto;
import com.Rightsy.demo.Dto.QuizResponse;

import java.util.List;

public interface QuizService {

    ApiResponseDto createQuiz(CreateQuizRequestDto createQuizRequestDto);
    List<QuizResponse> getAllQuiz();
    QuizResponse getQuizById(Long id);
    ApiResponseDto deleteQuizById(Long id);

}
