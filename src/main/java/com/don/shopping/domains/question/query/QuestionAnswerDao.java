package com.don.shopping.domains.question.query;

import com.don.shopping.domains.question.domain.QuestionAnswerEntity;

import java.util.Optional;

public interface QuestionAnswerDao {

    Optional<QuestionAnswerEntity> findOne(Long questionId);

    QuestionAnswerEntity findAnswerByQuestionId(Long questionId);

    void deleteQuestionAnswerOne(Long questionAnswerId);

    void deleteQuestionAnswer(Long questionId);

}
