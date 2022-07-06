package com.example.triviademo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String question;

    @ManyToOne
    private Answer correctAnswer;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Answer> answers;

    public Question(String question, Answer correctAnswer, Set<Answer> answers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }
}
