package com.Rightsy.demo.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Setter
public class SrtResponseDto {

    private Long id;
    private String topic;
    private List< SrtQuestionResponseDto> questions;

}
