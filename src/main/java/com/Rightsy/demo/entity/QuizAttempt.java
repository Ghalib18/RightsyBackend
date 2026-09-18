package com.Rightsy.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(table = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(table = "quiz_id")
    private Quiz quiz;

    private LocalDateTime attemptTime;
    private int score=0;
}
