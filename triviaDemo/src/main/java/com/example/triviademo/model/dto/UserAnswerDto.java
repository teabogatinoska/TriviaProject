package com.example.triviademo.model.dto;

import com.example.triviademo.model.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerDto implements Serializable {

    private String principal;

    private Long questionId;//Question objekt namesto id

    private Answer selectedAnswer;

    private Answer correctAnswer;

    private boolean checkAnswerIfTrue;//checkAnswerIfCorrect
}

