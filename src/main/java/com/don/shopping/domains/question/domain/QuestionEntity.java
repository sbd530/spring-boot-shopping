package com.don.shopping.domains.question.domain;

import com.don.shopping.common.logging.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
public class QuestionEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String productName;
    private Long userId;
    private String content;
    private String answer;
    private String userName;

    @Builder
    public QuestionEntity(Long id, Long productId, String productName, Long userId, String content, String answer) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.userId = userId;
        this.content = content;
        this.answer = answer;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
