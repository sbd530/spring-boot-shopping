package com.don.shopping.domains.home.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryHomeRepository {

    private static Map<Long, Boolean> answerRepository = new HashMap<>();

    public void saveQuestionToAnswer(Long questionId) {

        answerRepository.put(questionId, false);
    }

    public int getHaveToAnswer() {
        return answerRepository.size();
    }

    public void removeQuestionToAnswer(Long questionId) {

        if (answerRepository.containsKey(questionId)) {
            answerRepository.remove(questionId);
        }
    }
}
