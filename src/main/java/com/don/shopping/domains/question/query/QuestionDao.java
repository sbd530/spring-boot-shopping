package com.don.shopping.domains.question.query;

import com.don.shopping.domains.question.domain.QuestionEntity;

import java.util.List;

public interface QuestionDao {

    QuestionEntity findOne(Long questionid);

    List<QuestionEntity> findQuestionsByProductId(Long productId);

    void deleteQuestionOne(Long questionId);
}
