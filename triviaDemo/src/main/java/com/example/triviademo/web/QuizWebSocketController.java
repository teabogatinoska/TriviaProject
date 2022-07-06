package com.example.triviademo.web;


import com.example.triviademo.kafka.constant.AppConstant;
import com.example.triviademo.model.Question;
import com.example.triviademo.model.dto.AnsweredQuestionDto;
import com.example.triviademo.model.dto.QuestionKafkaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.example.triviademo.service.*;
import java.security.Principal;


@Controller
public class QuizWebSocketController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final QuestionService questionService;

    public QuizWebSocketController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * This method is used after the moderator opens the quiz
     *
     * @return dto of the first question to players enrolled in the quiz
     */
    @MessageMapping("/open")
    @SendTo("/quizzes/open")
    public QuestionKafkaDto returnQuestion() {

        Question question = this.questionService.findQuestionById(1L);
        return new QuestionKafkaDto(question.getId(), question.getQuestion(), question.getAnswers());
    }

    /**
     * This method is used to send the selected answer
     *
     * @return dto of the selected answer from the player
     */
    @MessageMapping("/send/answer")
    public void sendAnswer(AnsweredQuestionDto answeredQuestion, Principal principal) {

        try {
            answeredQuestion.setPrincipal(principal.getName());
            kafkaTemplate.send(AppConstant.TOPIC_NAME, answeredQuestion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Answered question was sent successfully.");
    }
}

