package com.example.triviademo.service;

import com.example.triviademo.model.Question;
import com.example.triviademo.model.exceptions.AnswerNotFoundException;
import com.example.triviademo.model.exceptions.QuestionNotFoundException;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    /**
     * This method is used to display all the questions in the database
     *
     * @return list of all the questions in the database
     */
    List<Question> findAllQuestions();

    /**
     * This method is used to find the question with the given id
     *
     * @param id The id of the question we want to obtain
     * @return the question with the given id
     * @throws QuestionNotFoundException when there is no question with the given id
     */
    Question findQuestionById(Long id);

    Optional<Question> findById(Long id);

    /**
     * This method is used to create a new question and save it in the database
     *
     * @param question
     * @param correctAnswerId
     * @param answerIds
     * @return the question that is created
     */
    Optional<Question> saveQuestion(String question, Long correctAnswerId, List<Long> answerIds);

    /**
     * This method is used to update a question given the arguments it takes
     *
     * @param id
     * @param question
     * @param correctAnswerId
     * @param answerIds
     * @return the question that is updated
     */
    Optional<Question> updateQuestion(Long id, String question, Long correctAnswerId, List<Long> answerIds);

    /**
     * This method should delete the question that has the appropriate identifier
     *
     * @param id
     * @return the HTTP status of the action completed
     */
    void deleteQuestionById(Long id);

    /**
     * This method checks if a question with the specified id is answered correctly
     *
     * @param questionId
     * @param selectedAnswerId
     * @throws AnswerNotFoundException if the question with the specified id does not exist
     */
    boolean checkAnsweredQuestion(Long questionId, Long selectedAnswerId);

}
