package com.don.shopping.domains.review.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.jdo.annotations.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@Getter @Setter //setter은 지우는게 좋음
@NoArgsConstructor
public class ReviewEntity {

    @Id @GeneratedValue
    private Long id;   //review 의 id

    private Long productId; //리뷰할 상품id

    private Long userId;  //userId

    private String content;  //리뷰할 내용
    
    private int rating; // 별점

    private LocalDateTime reviewTime; //리뷰 작성 시간



    @Builder
    public ReviewEntity(Long id, Long productId, Long userId, String content, int rating, LocalDateTime reviewTime) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.content = content;
        this.rating = rating;
        this.reviewTime = reviewTime;
    }
    //==비즈니스 로직==//
}
