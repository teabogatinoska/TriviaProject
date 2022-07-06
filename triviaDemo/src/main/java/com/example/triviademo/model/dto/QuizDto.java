package com.example.triviademo.model.dto;

import com.example.triviademo.model.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {

    private Long id;

    @NotEmpty(message = "The name should not be empty")
    private String name;

    @NotNull(message = "The date should not be null")
    @Future(message = "Quiz date should not be in the past")
    private LocalDateTime dateUTC;

    @NotNull(message = "The prize should not be null")
    @Min(value = 0, message = "The prize must not be negative value")
    private Integer prizeInUsd;

    @NotNull(message = "The status should not be null")
    private QuizStatus status;

   private List<Long> questionsIds;
}
