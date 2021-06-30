package com.don.shopping.domains.review.domain;

import com.don.shopping.common.logging.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Getter @Setter
@NoArgsConstructor
public class ReviewEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long userId;
    private String userName;
    private String content;
    private int rating;
    private String productName;

    @Builder
    public ReviewEntity(Long id, Long productId, Long userId, String userName, String content, int rating,String productName) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.rating = rating;
        this.productName = productName;
    }
}
