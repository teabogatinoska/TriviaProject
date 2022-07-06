package com.example.triviademo.web;

import com.example.triviademo.model.Question;
import com.example.triviademo.model.Quiz;
import com.example.triviademo.model.dto.QuestionDto;
import com.example.triviademo.model.exceptions.QuestionNotFoundException;
import com.example.triviademo.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/questions")
public class QuestionRestController {

    private final QuestionService questionService;

    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * This method is used to display all the questions in the database
     * The method should be mapped on path '/api/questions'
     *
     * @return list of all the questions in the database
     */
    @GetMapping
    public ResponseEntity<List<Question>> findAll() {
        return ResponseEntity.ok().body(this.questionService.findAllQuestions());
    }

    /**
     * This method is used to find the question with the given id
     * The method should be mapped on path '/api/questions/[id]'
     *
     * @param id The id of the question we want to obtain
     * @return the question with the given id
     * @throws QuestionNotFoundException when there is no question with the given id
     */
    @GetMapping("/{id}")
    private ResponseEntity<Question> findById(@PathVariable Long id) {
        return this.questionService.findById(id)
                .map(question -> ResponseEntity.ok().body(question))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    /**
     * This method is used to create a new question and save it in the database
     * The method should be mapped on path '/api/questions/add-question'
     *
     * @param questionDto
     * @return the question that is created
     */
    @PostMapping("/add-question")
    private ResponseEntity<Question> save(@RequestBody @Valid QuestionDto questionDto) {
        return this.questionService.saveQuestion(questionDto.getQuestion(), questionDto.getCorrectAnswer(), questionDto.getAnswersIds())
                .map(question -> ResponseEntity.ok().body(question))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * This method is used to update a question given the arguments it takes
     * The method should be mapped on path '/api/questions/edit-question/[id]'
     *
     * @param id
     * @param questionDto
     * @return the question that is updated
     */
    @PutMapping("/edit-question/{id}")
    private ResponseEntity<Question> update(@PathVariable Long id, @RequestBody @Valid QuestionDto questionDto) {
        return this.questionService.updateQuestion(id, questionDto.getQuestion(), questionDto.getCorrectAnswer(), questionDto.getAnswersIds())
                .map(question -> ResponseEntity.ok().body(question))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * This method should delete the question that has the appropriate identifier
     * The method should be mapped on path '/api/questions/delete-question/[id]'
     *
     * @param id
     * @return the HTTP status of the action completed
     */
    @DeleteMapping("/delete-question/{id}")
    private ResponseEntity<Quiz> deleteById(@PathVariable Long id) {
        this.questionService.deleteQuestionById(id);
        if (this.questionService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
