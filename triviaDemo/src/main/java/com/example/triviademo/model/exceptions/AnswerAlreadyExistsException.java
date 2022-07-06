package com.example.triviademo.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AnswerAlreadyExistsException extends RuntimeException{

    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public AnswerAlreadyExistsException(String answer) {

        super(String.format("Answer %s already exists", answer));
        this.answer = answer;

    }
}
