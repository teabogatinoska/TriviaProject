package com.example.triviademo.kafka.consumer;

import com.example.triviademo.kafka.constant.AppConstant;
import com.example.triviademo.model.Answer;
import com.example.triviademo.model.dto.AnsweredQuestionDto;
import com.example.triviademo.model.dto.UserAnswerDto;
import com.example.triviademo.repository.UserAnswerRepository;
import com.example.triviademo.service.AnswerService;
import com.example.triviademo.service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private final UserAnswerRepository userAnswerRepository;

    private final AnswerService answerService;

    private final QuestionService questionService;

    public Consumer(UserAnswerRepository userAnswerRepository, AnswerService answerService, QuestionService questionService) {
        this.userAnswerRepository = userAnswerRepository;
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @Cacheable(value = "userAnswers", key = "#answeredQuestion.principal")
    @KafkaListener(groupId = AppConstant.GROUP_ID_JSON, topics = AppConstant.TOPIC_NAME, containerFactory = AppConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
    public void receivedMessage(AnsweredQuestionDto answeredQuestionDto) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(answeredQuestionDto);
        logger.info("Json message received through Kafka listener " + jsonString);
        System.out.println("Message received through Kafka listener" + jsonString);

        Answer selectedAnswer = this.answerService.findAnswerById(answeredQuestionDto.getSelectedAnswerId());//userSelectedAnswer
        Answer correctAnswer = this.questionService.findQuestionById(answeredQuestionDto.getQuestionId()).getCorrectAnswer();
        boolean checkAnswerIfTrue = this.questionService.checkAnsweredQuestion(answeredQuestionDto.getQuestionId(), answeredQuestionDto.getSelectedAnswerId());

        UserAnswerDto userAnswerDto = new UserAnswerDto(answeredQuestionDto.getPrincipal(), answeredQuestionDto.getQuestionId(), selectedAnswer, correctAnswer, checkAnswerIfTrue);
        this.userAnswerRepository.save(userAnswerDto);

        if(checkAnswerIfTrue){
            System.out.println("Correct answer selected");
        } else {
            System.out.println("Incorrect answer selected");
        }

    }
}