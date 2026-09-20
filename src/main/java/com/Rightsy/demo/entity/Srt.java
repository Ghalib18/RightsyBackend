package com.Rightsy.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Srt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String topic;

    @OneToMany(mappedBy = "srt",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SrtQuestion> questions =new ArrayList<>();
}
