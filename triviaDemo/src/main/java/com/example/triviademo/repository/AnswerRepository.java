package com.example.triviademo.repository;

import com.example.triviademo.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Optional<Answer> findByAnswer(String answer);
}
