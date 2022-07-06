package com.example.triviademo.web;


import com.example.triviademo.model.Quiz;
import com.example.triviademo.model.dto.QuizDto;
import com.example.triviademo.model.exceptions.QuizNotFoundException;
import com.example.triviademo.service.QuestionService;
import com.example.triviademo.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/quizzes")
public class QuizRestController {

    private final QuizService quizService;

    private final QuestionService questionService;

    public QuizRestController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    /**
     * This method is used to display all the quizzes in the database
     * The method should be mapped on path '/api/quizzes'
     *
     * @return list of all the quizzes in the database
     */
    @GetMapping
    public ResponseEntity<List<Quiz>> findAll() {
        return ResponseEntity.ok().body(this.quizService.findAllQuizzes());
    }

    /**
     * This method is used to find the quiz with the given id
     * The method should be mapped on path '/api/quizzes/[id]'
     *
     * @param id The id of the quiz we want to obtain
     * @return the quiz with the given id
     * @throws QuizNotFoundException when there is no quiz with the given id
     */
    @GetMapping("/{id}")
    private ResponseEntity<Quiz> findById(@PathVariable Long id) {
        return this.quizService.findQuizById(id)
                .map(quiz -> ResponseEntity.ok().body(quiz))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    /**
     * This method is used to create a new quiz and save it in the database
     * The method should be mapped on path '/api/quizzes/add-quiz'
     *
     * @param quizDto
     * @return the quiz that is created
     */
    @PostMapping("/add-quiz")
    private ResponseEntity<Quiz> save(@RequestBody @Valid QuizDto quizDto) {
        return this.quizService.saveQuiz(quizDto.getName(), quizDto.getDateUTC(), quizDto.getPrizeInUsd(), quizDto.getStatus(), quizDto.getQuestionsIds())
                .map(quiz -> ResponseEntity.ok().body(quiz))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * This method is used to update a quiz given the arguments it takes
     * The method should be mapped on path '/api/quizzes/edit-quiz/[id]'
     *
     * @param id
     * @param quizDto
     * @return the quiz that is updated
     */
    @PutMapping("/edit-quiz/{id}")
    private ResponseEntity<Quiz> update(@PathVariable Long id, @RequestBody @Valid QuizDto quizDto) {
        return this.quizService.updateQuiz(id, quizDto.getName(), quizDto.getDateUTC(), quizDto.getPrizeInUsd(), quizDto.getStatus(), quizDto.getQuestionsIds())
                .map(quiz -> ResponseEntity.ok().body(quiz))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * This method should delete the quiz that has the appropriate identifier
     * The method should be mapped on path '/api/quizzes/delete-quiz/[id]'
     *
     * @param id
     * @return the HTTP status of the action completed
     */
    @DeleteMapping("/delete-quiz/{id}")
    private ResponseEntity<Quiz> deleteById(@PathVariable Long id) {
        this.quizService.deleteQuizById(id);
        if (this.quizService.findQuizById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
