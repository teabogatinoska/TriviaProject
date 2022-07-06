package com.example.triviademo.service;




import com.example.triviademo.model.Answer;
import com.example.triviademo.model.exceptions.AnswerNotFoundException;
import com.example.triviademo.model.exceptions.AnswerAlreadyExistsException;

import java.util.List;
import java.util.Optional;

public interface AnswerService {

    /**
     * This method is used to display all the answers in the database
     *
     * @return list of all the answers in the database
     */
    List<Answer> findAllAnswers();

    /**
     * This method is used to find the answer with the given id
     *
     * @param id The id of the question we want to obtain
     * @return the answer with the given id
     * @throws AnswerNotFoundException when there is no answer with the given id
     */
    Answer findAnswerById(Long id);

    Optional<Answer> findById(Long id);

    /**
     * This method is used to create a new answer and save it in the database
     *
     * @param  answer
     * @return the answer that is created
     * @throws AnswerAlreadyExistsException when the answer already exists
     */
    Optional<Answer> saveAnswer(String answer);

    /**
     * This method is used to update an answer given the arguments it takes
     *
     * @param id
     * @param answer
     * @return the answer that is updated
     */
    Optional<Answer> updateAnswer(Long id, String answer);

    /**
     * This method should delete the answer that has the appropriate identifier
     *
     * @param id
     * @return the HTTP status of the action completed
     */
    void deleteAnswerById(Long id);
}
