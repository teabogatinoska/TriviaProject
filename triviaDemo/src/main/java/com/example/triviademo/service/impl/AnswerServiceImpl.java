package com.example.triviademo.service.impl;


import com.example.triviademo.model.Answer;
import com.example.triviademo.model.exceptions.AnswerAlreadyExistsException;
import com.example.triviademo.model.exceptions.AnswerNotFoundException;
import com.example.triviademo.repository.AnswerRepository;
import com.example.triviademo.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Answer> findAllAnswers() {
        return this.answerRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Answer findAnswerById(Long id) {
        return this.answerRepository.findById(id).orElseThrow(() -> new AnswerNotFoundException(id));
    }

    @Override
    public Optional<Answer> findById(Long id) {
        return this.answerRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Answer> saveAnswer(String answer) {
        if (this.answerRepository.findByAnswer(answer).isPresent()) {
            throw new AnswerAlreadyExistsException(answer);
        }
        Answer newAnswer = new Answer(answer);
        this.answerRepository.save(newAnswer);
        return Optional.of(newAnswer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Answer> updateAnswer(Long id, String answer) {
        Answer foundAnswer = this.answerRepository.findById(id).orElseThrow(() -> new AnswerNotFoundException(id));
        foundAnswer.setAnswer(answer);
        this.answerRepository.save(foundAnswer);
        return Optional.of(foundAnswer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAnswerById(Long id) {
        Answer answer = this.answerRepository.findById(id).orElseThrow(() -> new AnswerNotFoundException(id));
        this.answerRepository.delete(answer);
    }
}
