package com.Rightsy.demo.controller;


import com.Rightsy.demo.Dto.ApiResponseDto;
import com.Rightsy.demo.Dto.CreateQuizRequestDto;
import com.Rightsy.demo.Dto.QuizAnswerReqDto;
import com.Rightsy.demo.Dto.QuizResponse;
import com.Rightsy.demo.Service.QuizService;
import com.Rightsy.demo.entity.QuizAttempt;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PostMapping("/admin/create")
    public ResponseEntity<ApiResponseDto> createQuiz(@Valid @RequestBody CreateQuizRequestDto createQuizRequestDto){
        return ResponseEntity.ok(quizService.createQuiz(createQuizRequestDto));
    }

    @GetMapping("/quizzes")
    public ResponseEntity<List<QuizResponse>> getAllQuizzes(){
        return ResponseEntity.ok(quizService.getAllQuiz());
    }

    @GetMapping("/quiz/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable Long id){
        return ResponseEntity.ok(quizService.getQuizById(id));
    }
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<ApiResponseDto> deleteQuiz(@PathVariable Long id){
        return ResponseEntity.ok(quizService.deleteQuizById(id));
    }

    @PostMapping("/submitQuiz")
    public  ResponseEntity<ApiResponseDto> submitQuiz(@RequestBody QuizAnswerReqDto quizAnswerReqDto){
        return ResponseEntity.ok(quizService.submitQuiz(quizAnswerReqDto));
    }
    @GetMapping("/getAttempts/{id}")
    public ResponseEntity<List<QuizAttempt>> getAttempts(@PathVariable Long id){
        return ResponseEntity.ok(quizService.getAttempts(id));
    }

}
