package com.example.triviademo.service.impl;

import com.example.triviademo.model.Question;
import com.example.triviademo.model.Quiz;
import com.example.triviademo.model.enums.QuizStatus;
import com.example.triviademo.model.exceptions.QuizNotFoundException;
import com.example.triviademo.repository.QuestionRepository;
import com.example.triviademo.repository.QuizRepository;
import com.example.triviademo.service.QuizService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Quiz> findAllQuizzes() {
        return this.quizRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Quiz> findQuizById(Long id) {
        return this.quizRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Quiz> saveQuiz(String name, LocalDateTime dateUTC, Integer prizeInUsd, QuizStatus status, List<Long> questionsIds) {
        Set<Question> questions = new HashSet<>(this.questionRepository.findAllById(questionsIds));
        Quiz newQuiz = new Quiz(name, dateUTC, prizeInUsd, status, questions);
        this.quizRepository.save(newQuiz);
        return Optional.of(newQuiz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Quiz> updateQuiz(Long id, String name, LocalDateTime dateUTC, Integer prizeInUsd, QuizStatus status, List<Long> questionsIds) {
        Quiz foundQuiz = this.quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(id));
        Set<Question> questions = new HashSet<>(this.questionRepository.findAllById(questionsIds));
        foundQuiz.setName(name);
        foundQuiz.setDateUTC(dateUTC);
        foundQuiz.setPrizeInUsd(prizeInUsd);
        foundQuiz.setStatus(status);
        foundQuiz.setQuestions(questions);
        this.quizRepository.save(foundQuiz);
        return Optional.of(foundQuiz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteQuizById(Long id) {
        this.quizRepository.deleteById(id);
    }

}
