package com.Rightsy.demo.Service;

import com.Rightsy.demo.Dto.*;
import com.Rightsy.demo.entity.Quiz;
import com.Rightsy.demo.entity.QuizAttempt;
import com.Rightsy.demo.entity.QuizQuestion;
import com.Rightsy.demo.entity.User;
import com.Rightsy.demo.repository.QuizAttemptRepo;
import com.Rightsy.demo.repository.QuizRepo;
import com.Rightsy.demo.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepo quizRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    private final QuizAttemptRepo quizAttemptRepo;

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

    @Override
    public ApiResponseDto submitQuiz(QuizAnswerReqDto quizAnswerReqDto) {

        //Get the Logged In User from JWT
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

       String email= authentication.getName();

        User user= userRepo.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException(("user not found")));

        // Find the quiz from QuizRepo

        Quiz quiz = quizRepo.findById(quizAnswerReqDto.getQuizId())
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found"));

        List<QuizAttempt> attempts= quizAttemptRepo.findByUserIdAndQuizId(user.getId(),quizAnswerReqDto.getQuizId());

        // Calculating the score for this attempt
        int score=0;

        for(QuestionAnsReqDto q:quizAnswerReqDto.getAnswers()){

            QuizQuestion quizQuestion=quiz.getQuestions()
                    .stream()
                    .filter(qt->qt.getQuestionId().equals(q.getQuestionId()))
                    .findFirst()
                    .orElseThrow(()-> new EntityNotFoundException("question not found"));

            if(quizQuestion.getAnswer().equalsIgnoreCase(q.getAnswer())){
                score=score+1;
            }
        }

        // save the attempt

        QuizAttempt quizAttempt=new QuizAttempt();

        quizAttempt.setQuiz(quiz);
        quizAttempt.setUser(user);
        quizAttempt.setAttemptTime(LocalDateTime.now());
        quizAttempt.setScore(score);

        quizAttemptRepo.save(quizAttempt);

        // Checking whether this is user first Attempt or not , if not then add to the total score

        if(attempts.isEmpty()){
            user.setTotalScore(user.getTotalScore()+ score*10);
        }
        userRepo.save(user);
        return new ApiResponseDto(  "Quiz submitted successfully. Score: " + score*10 +"/40",true);
    }

    @Override
    public List<QuizAttempt> getAttempts(Long id) {
        //Get the Logged In User from JWT
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        String email= authentication.getName();

        User user= userRepo.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException(("user not found")));

        List<QuizAttempt> attemptList=quizAttemptRepo.findByUserIdAndQuizId(user.getId(),id);

        return attemptList;

    }
}
