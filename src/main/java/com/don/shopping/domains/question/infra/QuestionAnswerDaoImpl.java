package com.don.shopping.domains.question.infra;

import com.don.shopping.domains.question.domain.QQuestionAnswerEntity;
import com.don.shopping.domains.question.domain.QuestionAnswerEntity;
import com.don.shopping.domains.question.domain.QuestionEntity;
import com.don.shopping.domains.question.query.QuestionAnswerDao;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuestionAnswerDaoImpl implements QuestionAnswerDao {

    private final JPAQueryFactory query;
    private final QQuestionAnswerEntity qQuestionAnswerEntity = QQuestionAnswerEntity.questionAnswerEntity;



    @Override //댓글하나 조회
    public Optional<QuestionAnswerEntity> findOne(Long questionAnswerId) {
        Optional<QuestionAnswerEntity> questionAnswerEntity = Optional.ofNullable(query.selectFrom(qQuestionAnswerEntity)
                .where(qQuestionAnswerEntity.questionId.eq(questionAnswerId))
                .fetchOne());
        return questionAnswerEntity;
    }

    @Override //그 질문에 대한 답변들 조회
    public List<QuestionAnswerEntity> findQuestionByQuestionId(Long questionId) {
        List<QuestionAnswerEntity> questionAnswerEntityList = query.selectFrom(qQuestionAnswerEntity)
                .where(qQuestionAnswerEntity.questionId.eq(questionId))
                .fetch();

        return questionAnswerEntityList;
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
