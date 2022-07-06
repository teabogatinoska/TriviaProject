package com.example.triviademo.service;

import com.example.triviademo.model.Quiz;
import com.example.triviademo.model.enums.QuizStatus;
import com.example.triviademo.model.exceptions.QuizNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface QuizService {

    /**
     * This method is used to display all the quizzes in the database
     *
     * @return list of all the quizzes in the database
     */
    List<Quiz> findAllQuizzes();

    /**
     * This method is used to find the quiz with the given id
     *
     * @param id The id of the quiz we want to obtain
     * @return the quiz with the given id
     * @throws QuizNotFoundException when there is no quiz with the given id
     */
    Optional<Quiz> findQuizById(Long id);


    /**
     * This method is used to create a new quiz and save it in the database
     *
     * @param name
     * @param dateUTC
     * @param prizeInUsd
     * @return the quiz that is created
     */
    Optional<Quiz> saveQuiz(String name, LocalDateTime dateUTC, Integer prizeInUsd, QuizStatus status, List<Long> questionsIds);

    /**
     * This method is used to update a quiz given the arguments it takes
     *
     * @param id
     * @param name
     * @param dateUTC
     * @param prizeInUsd
     * @return the quiz that is updated
     */
    Optional<Quiz> updateQuiz(Long id, String name, LocalDateTime dateUTC, Integer prizeInUsd, QuizStatus status, List<Long> questionsIds);

    /**
     * This method should delete the quiz that has the appropriate identifier
     *
     * @param id
     * @return the HTTP status of the action completed
     */
    void deleteQuizById(Long id);


}
