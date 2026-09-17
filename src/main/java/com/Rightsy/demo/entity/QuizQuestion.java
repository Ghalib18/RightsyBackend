package com.Rightsy.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class QuizQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String answer;

    @ManyToOne
    @JoinColumn(name="quiz_id")
    private Quiz quiz;
}
