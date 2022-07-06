package com.example.triviademo.model.exceptions;

import com.example.triviademo.model.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CorrectAnswerNotFoundException extends RuntimeException{

    private Answer answer;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public CorrectAnswerNotFoundException(Answer answer) {

        super(String.format("Correct answer: %s is not found in question answers", answer));
        this.answer = answer;

    }
}
