package com.don.shopping.domains.review.service;

import com.don.shopping.domains.review.domain.ReviewEntity;
import com.don.shopping.domains.review.domain.ReviewRepository;
import com.don.shopping.domains.review.infra.ReviewDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class ReviewServiceTest {

    @Autowired ReviewService reviewService;
    @Autowired ReviewRepository reviewRepository;
    @Autowired
    ReviewDaoImpl reviewDao;


    @Test
    //@Rollback(false) //insert문이보여지게 됨 true면 insert안하고 롤백되버림
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