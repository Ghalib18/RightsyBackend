package com.Rightsy.demo.repository;

import com.Rightsy.demo.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepo extends JpaRepository<Quiz,Long> {

}
