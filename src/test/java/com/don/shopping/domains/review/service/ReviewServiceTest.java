package com.don.shopping.domains.review.service;

import com.don.shopping.domains.review.domain.ReviewEntity;
import com.don.shopping.domains.review.query.ReviewDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewServiceTest {

    @Autowired ReviewService reviewService;
    @Autowired
    ReviewDao reviewDao;

    @Test
    public void 리뷰등록() throws Exception{
        //given
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setContent("dd");
        //when
        Long saveId = reviewService.addReview(reviewEntity);
        //then
        assertEquals(reviewEntity,reviewDao.findOne(saveId));
    }


}