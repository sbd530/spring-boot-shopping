package com.don.shopping.domains.question.infra;

import com.don.shopping.domains.question.domain.QQuestionAnswerEntity;
import com.don.shopping.domains.question.domain.QuestionAnswerEntity;
import com.don.shopping.domains.question.query.QuestionAnswerDao;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuestionAnswerDaoImpl implements QuestionAnswerDao {

    private final JPAQueryFactory query;
    private final QQuestionAnswerEntity qQuestionAnswerEntity = QQuestionAnswerEntity.questionAnswerEntity;

    @Override
    public Optional<QuestionAnswerEntity> findOne(Long questionAnswerId) {
        Optional<QuestionAnswerEntity> questionAnswerEntity = Optional.ofNullable(query.selectFrom(qQuestionAnswerEntity)
                .where(qQuestionAnswerEntity.questionId.eq(questionAnswerId))
                .fetchOne());
        return questionAnswerEntity;
    }

    @Override
    public QuestionAnswerEntity findAnswerByQuestionId(Long questionId) {
        QuestionAnswerEntity questionAnswerEntity = query.selectFrom(qQuestionAnswerEntity)
                .where(qQuestionAnswerEntity.questionId.eq(questionId))
                .fetchOne();

        return questionAnswerEntity;
    }

    @Override
    @Transactional
    public void deleteQuestionAnswerOne(Long questionAnswerId) {
        query.delete(qQuestionAnswerEntity)
                .where(qQuestionAnswerEntity.id.eq(questionAnswerId))
                .execute();
    }

    @Override
    @Transactional
    public void deleteQuestionAnswer(Long questionId) {
        query.delete(qQuestionAnswerEntity)
                .where(qQuestionAnswerEntity.questionId.eq(questionId))
                .execute();
    }
}
