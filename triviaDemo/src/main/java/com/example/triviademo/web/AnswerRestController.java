package com.example.triviademo.web;


import com.example.triviademo.model.Answer;
import com.example.triviademo.model.dto.AnswerDto;
import com.example.triviademo.model.exceptions.AnswerNotFoundException;
import com.example.triviademo.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/answers")
public class AnswerRestController {

    private final AnswerService answerService;

    public AnswerRestController(AnswerService answerService) {
        this.answerService = answerService;
    }

    /**
     * This method is used to display all the answers in the database
     * The method should be mapped on path '/api/answers'
     *
     * @return list of all the answers in the database
     */
    @GetMapping
    public ResponseEntity<List<Answer>> findAll() {
        return ResponseEntity.ok().body(this.answerService.findAllAnswers());
    }

    /**
     * This method is used to find the answer with the given id
     * The method should be mapped on path '/api/answers/[id]'
     *
     * @param id The id of the answer we want to obtain
     * @return the answer with the given id
     * @throws AnswerNotFoundException when there is no answer with the given id
     */
    @GetMapping("/{id}")
    private ResponseEntity<Answer> findById(@PathVariable Long id) {
        return this.answerService.findById(id)
                .map(answer -> ResponseEntity.ok().body(answer))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    /**
     * This method is used to create a new answer and save it in the database
     * The method should be mapped on path '/api/answers/add-answer'
     *
     * @param answerDto
     * @return the answer that is created
     */
    @PostMapping("/add-answer")
    private ResponseEntity<Answer> save(@RequestBody @Valid AnswerDto answerDto) {
        return this.answerService.saveAnswer(answerDto.getAnswer())
                .map(answer -> ResponseEntity.ok().body(answer))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * This method is used to update an answer given the arguments it takes
     * The method should be mapped on path '/api/answers/edit-answer/[id]'
     *
     * @param id
     * @param answerDto
     * @return the answer that is updated
     */
    @PutMapping("/edit-answer/{id}")
    private ResponseEntity<Answer> update(@PathVariable Long id, @RequestBody @Valid AnswerDto answerDto) {
        return this.answerService.updateAnswer(id, answerDto.getAnswer())
                .map(answer -> ResponseEntity.ok().body(answer))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * This method should delete the answer that has the appropriate identifier
     * The method should be mapped on path '/api/answers/delete-answer/[id]'
     *
     * @param id
     * @return the HTTP status of the action completed
     */
    @DeleteMapping("/delete-answer/{id}")
    private ResponseEntity<Answer> deleteById(@PathVariable Long id) {
        this.answerService.deleteAnswerById(id);
        if (this.answerService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
