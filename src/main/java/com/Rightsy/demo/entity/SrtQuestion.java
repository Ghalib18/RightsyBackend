package com.Rightsy.demo.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SrtQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String imageUrl;

    @ElementCollection
    private List<String> prerequisites;

    private String optionA;
    private String noteA;
    private String optionB;
    private String noteB;
    private String optionC;
    private String noteC;
    private String optionD;
    private String noteD;

    private String answer;

    @ManyToOne
    @JoinColumn(name="srt_id")
    private Srt srt;

}
