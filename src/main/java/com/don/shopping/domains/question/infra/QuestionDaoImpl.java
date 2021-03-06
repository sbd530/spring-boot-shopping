package com.don.shopping.domains.question.infra;

import com.don.shopping.domains.question.domain.QQuestionEntity;
import com.don.shopping.domains.question.domain.QuestionEntity;
import com.don.shopping.domains.question.query.QuestionDao;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {

    private final JPAQueryFactory query;
    private final QQuestionEntity qQuestionEntity = QQuestionEntity.questionEntity;

    @Override
    public QuestionEntity findOne(Long questionid) {
        QuestionEntity questionEntity = query.selectFrom(qQuestionEntity)
                .where(qQuestionEntity.id.eq(questionid))
                .fetchOne();
        return questionEntity;
    }

    //해당 상품의 전체 질문
    @Override
    public List<QuestionEntity> findQuestionsByProductId(Long productId) {

        List<QuestionEntity> questionEntityList = query.selectFrom(qQuestionEntity)
                .where(qQuestionEntity.productId.eq(productId))
                .fetch();
        return questionEntityList;
    }

    @Override
    @Transactional
    public void deleteQuestionOne(Long questionId) {
        query.delete(qQuestionEntity)
                .where(qQuestionEntity.id.eq(questionId))
                .execute();
    }
}
