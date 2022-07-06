package com.example.triviademo.model.dto;

import com.example.triviademo.model.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionKafkaDto {

    private Long id;

    private String question;

    private Set<Answer> answers;
}