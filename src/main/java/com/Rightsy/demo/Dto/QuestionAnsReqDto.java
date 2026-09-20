package com.Rightsy.demo.Dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class QuestionAnsReqDto {

    private Long questionId;
    private String answer;

}
