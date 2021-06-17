package com.don.shopping.domains.review.infra;

import com.don.shopping.domains.product.domain.QProductEntity;
import com.don.shopping.domains.review.domain.QReviewEntity;
import com.don.shopping.domains.review.domain.ReviewEntity;
import com.don.shopping.domains.review.query.ReviewDao;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ReviewDaoImpl implements ReviewDao {

    private final JPAQueryFactory query;
    private final QReviewEntity qReviewEntity = QReviewEntity.reviewEntity;
    private final QProductEntity qProductEntity = QProductEntity.productEntity;


    @Override
    public ReviewEntity findOne(Long reviewid) {
        ReviewEntity reviewEntity = query.selectFrom(qReviewEntity)
                .where(qReviewEntity.id.eq(reviewid))
                .fetchOne();
        return reviewEntity;
    }

    @Override
    public List<ReviewEntity> findReviewsByProductId(Long productId) { //전체 리뷰

        List<ReviewEntity> reviewEntityList = query.selectFrom(qReviewEntity)
                .where(qReviewEntity.productId.eq(productId))
                .fetch();

        return reviewEntityList;
    }
    @Transactional
    @Override
    public void deleteReviewOne(Long reviewid) {
        query.delete(qReviewEntity)
                .where(qReviewEntity.id.eq(reviewid))
                .execute();
    }

}
