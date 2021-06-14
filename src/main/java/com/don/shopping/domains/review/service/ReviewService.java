
package com.don.shopping.domains.review.service;





import com.don.shopping.domains.review.domain.ReviewEntity;
import com.don.shopping.domains.review.domain.ReviewRepository;
import com.don.shopping.domains.review.query.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor //의존주입해주는 뇨속
public class ReviewService {

    private final ReviewRepository reviewRepository;


    public Long createReview(ReviewDTO reviewDTO) throws Exception{ //리뷰쓴사람 이름(이름은 세션으로 받아야할듯)과 dto를 받음

        ReviewEntity reviewEntity = new ReviewEntity(
                reviewDTO.getReviewid(),
                reviewDTO.getReviewproductname(),
                reviewDTO.getReviewcontent(),
                reviewDTO.getReviewSaveFileName(),
                reviewDTO.getReviewOriginalFileName(),
                reviewDTO.getStarscore(),
                reviewDTO.getReviewdate(),
                reviewDTO.getReviewerid()
        );

        return reviewRepository.save(reviewEntity).getReviewid();
    }

    public List<ReviewDTO> searchAllDesc(){
        return reviewRepository.findAll().stream()
                .map(ReviewDTO::new)
                .collect(Collectors.toList());
    }

}

