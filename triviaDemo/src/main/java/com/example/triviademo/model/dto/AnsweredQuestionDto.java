package com.example.triviademo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnsweredQuestionDto {

    private String principal;

    private Long questionId;

    private Long selectedAnswerId;


}
