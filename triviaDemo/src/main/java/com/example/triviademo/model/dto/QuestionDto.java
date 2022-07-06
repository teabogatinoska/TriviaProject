package com.example.triviademo.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    private Long id;

    @NotEmpty
    @Size(min = 1, max = 200)
    private String question;

    @NotEmpty
    private Long correctAnswer;

    @NotNull
    @Size(min = 3, max = 3, message = "Every question must have 3 answers")
    private List<Long> answersIds;
}
