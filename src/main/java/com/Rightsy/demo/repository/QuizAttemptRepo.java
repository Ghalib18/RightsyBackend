package com.Rightsy.demo.repository;

import com.Rightsy.demo.entity.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizAttemptRepo  extends JpaRepository<QuizAttempt,Long> {

    List<QuizAttempt> findByUserIdAndQuizQuizId(Long userId, Long quizId);
}
