package com.don.shopping.domains.review.domain;

import com.don.shopping.common.logging.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.jdo.annotations.Embedded;
import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;

@Entity
@Table(name = "review")
@Getter @Setter //setter은 지우는게 좋음
@NoArgsConstructor
public class ReviewEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   //review 의 id
    private Long productId; //리뷰할 상품id
    private Long userId;  //userId
    private String userName;//userName
    private String content;  //리뷰할 내용
    private int rating; // 별점


    @Builder
    public ReviewEntity(Long id, Long productId, Long userId, String userName, String content, int rating) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.rating = rating;
    }
}
