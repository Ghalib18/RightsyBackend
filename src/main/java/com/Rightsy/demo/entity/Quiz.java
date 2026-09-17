package com.Rightsy.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quiz_Id;

    private String topic;

    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<QuizQuestion> questions;

}
