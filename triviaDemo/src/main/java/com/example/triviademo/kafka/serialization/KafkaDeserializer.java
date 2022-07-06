package com.example.triviademo.kafka.serialization;


import com.example.triviademo.model.dto.AnsweredQuestionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class KafkaDeserializer implements Deserializer<AnsweredQuestionDto> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public AnsweredQuestionDto deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                return null;
            }
            return objectMapper.readValue(new String(data, "UTF-8"), AnsweredQuestionDto.class);
        } catch (Exception e) {
            throw new SerializationException("Can't deserializing byte[] to AnsweredQuestionWebSocketDto");
        }
    }

    @Override
    public void close() {
    }
}
