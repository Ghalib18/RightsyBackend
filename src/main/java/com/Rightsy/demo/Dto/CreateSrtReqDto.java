package com.Rightsy.demo.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class CreateSrtReqDto {

    @NotBlank
    private String topic;
    @Size(min=3,max=3)
    private List<@Valid SrtQuestionReqDto> questions;

}
