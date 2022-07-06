package com.example.triviademo.kafka.serialization;

import com.example.triviademo.model.dto.AnsweredQuestionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class KafkaSerializer  implements Serializer<AnsweredQuestionDto> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, AnsweredQuestionDto data) {
        try {
            if (data == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing the message to byte[]");
        }
    }

    @Override
    public void close() {
    }
}