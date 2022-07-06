package com.example.triviademo.repository;

import com.example.triviademo.model.dto.UserAnswerDto;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserAnswerRepository {

    private HashOperations hashOperations;

    public UserAnswerRepository (RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void save(UserAnswerDto userAnswerDto) {

        hashOperations.put("userAnswers", userAnswerDto.getPrincipal(), userAnswerDto);
    }

}
