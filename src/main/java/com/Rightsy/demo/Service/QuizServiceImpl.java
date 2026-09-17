package com.Rightsy.demo.Service;

import com.Rightsy.demo.Dto.ApiResponseDto;
import com.Rightsy.demo.Dto.CreateQuizRequestDto;
import com.Rightsy.demo.Dto.QuizQuestionRequestDto;
import com.Rightsy.demo.Dto.QuizResponse;
import com.Rightsy.demo.entity.Quiz;
import com.Rightsy.demo.entity.QuizQuestion;
import com.Rightsy.demo.repository.QuizRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepo quizRepo;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponseDto createQuiz(CreateQuizRequestDto createQuizRequestDto) {
        Quiz quiz=new Quiz();

        quiz.setTopic(createQuizRequestDto.getTopic());

        for(QuizQuestionRequestDto q:createQuizRequestDto.getQuestions()){
            QuizQuestion question=new QuizQuestion();

            question.setQuestion(q.getQuestion());
            question.setOptionA(q.getOptionA());
            question.setOptionB(q.getOptionB());
            question.setOptionC((q.getOptionC()));
            question.setOptionD((q.getOptionD()));
            question.setAnswer(q.getAnswer());
            question.setQuiz(quiz);

            quiz.getQuestions().add(question);
        }
        quizRepo.save(quiz);


        return new ApiResponseDto("Quiz has been added Successfully",true);
    }

    @Override
    public List<QuizResponse> getAllQuiz() {
        List<Quiz> quizzes=quizRepo.findAll();
        return quizzes.stream()
                .map(quiz->modelMapper.map(quiz,QuizResponse.class)).toList();
    }

    @Override
    public QuizResponse getQuizById(Long id) {
        Quiz quiz=quizRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Quiz not found"));

        return modelMapper.map(quiz,QuizResponse.class);
    }

    @Override
    public ApiResponseDto deleteQuizById(Long id) {
        Quiz quiz=quizRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Quiz not found"));

        quizRepo.delete(quiz);

        return new ApiResponseDto("Quiz with  "+id+"has been deleted",true);
    }
}
