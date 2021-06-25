package com.don.shopping.domains.question.query;

import com.don.shopping.domains.question.domain.QuestionAnswerEntity;

import java.util.List;
import java.util.Optional;

public interface QuestionAnswerDao {

    Optional<QuestionAnswerEntity> findOne(Long questionId);

    List<QuestionAnswerEntity> findQuestionByQuestionId(Long questionId);

    void deleteQuestionAnswerOne(Long questionAnswerId);

    void deleteQuestionAnswer(Long questionId);

}
