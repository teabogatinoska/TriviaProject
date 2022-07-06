package com.example.triviademo.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnswerNotFoundException extends RuntimeException{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnswerNotFoundException(Long id) {

        super(String.format("Answer with id: %d was not found", id));
        this.id = id;

    }
}
