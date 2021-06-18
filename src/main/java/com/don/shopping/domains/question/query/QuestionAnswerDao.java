package com.don.shopping.domains.question.query;

import com.don.shopping.domains.question.domain.QuestionAnswerEntity;

import java.util.List;

public interface QuestionAnswerDao {

    QuestionAnswerEntity findOne(Long questionId);

    List<QuestionAnswerEntity> findQuestionByQuestionId(Long questionId);

    void deleteQuestionAnswerOne(Long questionAnswerId);

}
