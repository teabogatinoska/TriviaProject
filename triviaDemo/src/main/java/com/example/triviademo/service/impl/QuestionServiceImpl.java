package com.example.triviademo.service.impl;

import com.example.triviademo.model.Answer;
import com.example.triviademo.model.Question;

import com.example.triviademo.model.exceptions.AnswerNotFoundException;
import com.example.triviademo.model.exceptions.CorrectAnswerNotFoundException;
import com.example.triviademo.model.exceptions.QuestionNotFoundException;
import com.example.triviademo.repository.AnswerRepository;
import com.example.triviademo.repository.QuestionRepository;
import com.example.triviademo.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Question> findAllQuestions() {
        return this.questionRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Question findQuestionById(Long id) {
        return this.questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
    }

    @Override
    public Optional<Question> findById(Long id) {
        return this.questionRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Question> saveQuestion(String question, Long correctAnswerId, List<Long> answerIds) {
        Set<Answer> answers = new HashSet<>(this.answerRepository.findAllById(answerIds));
        Answer correctAnswer = this.answerRepository.findById(correctAnswerId).orElseThrow(() -> new AnswerNotFoundException(correctAnswerId));

        if (answers.contains(correctAnswer)) {
            Question newQuestion = new Question(question, correctAnswer, answers);
            this.questionRepository.save(newQuestion);
            return Optional.of(newQuestion);
        } else throw new CorrectAnswerNotFoundException(correctAnswer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Question> updateQuestion(Long id, String question, Long correctAnswerId, List<Long> answerIds) {
        Question foundQuestion = this.questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
        Answer correctAnswer = this.answerRepository.findById(correctAnswerId).orElseThrow(() -> new AnswerNotFoundException(correctAnswerId));
        Set<Answer> answers = new HashSet<>(this.answerRepository.findAllById(answerIds));

        foundQuestion.setQuestion(question);
        foundQuestion.setCorrectAnswer(correctAnswer);
        foundQuestion.setAnswers(answers);
        this.questionRepository.save(foundQuestion);
        return Optional.of(foundQuestion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteQuestionById(Long id) {
        this.questionRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkAnsweredQuestion(Long questionId, Long selectedAnswerId) {

        Question foundQuestion = findQuestionById(questionId);
        Answer selectedAnswer = this.answerRepository.findById(selectedAnswerId).orElseThrow(() -> new AnswerNotFoundException(selectedAnswerId));

        return foundQuestion.getCorrectAnswer().equals(selectedAnswer);
    }
}
