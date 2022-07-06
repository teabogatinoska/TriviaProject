package com.example.triviademo.model;

import com.example.triviademo.model.enums.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity(name = "quizzes")
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @Column(name = "date_time_UTC")
    private LocalDateTime dateUTC;

    @Column(name = "price_in_usd")
    private Integer prizeInUsd;

    @Enumerated(EnumType.STRING)
    private QuizStatus status;

    @ManyToMany
    private Set<Question> questions;

    public Quiz(String name, LocalDateTime dateUTC, Integer prizeInUsd, QuizStatus status, Set<Question> questions) {
        this.name = name;
        this.dateUTC = dateUTC;
        this.prizeInUsd = prizeInUsd;
        this.status = status;
        this.questions = questions;
    }
}
