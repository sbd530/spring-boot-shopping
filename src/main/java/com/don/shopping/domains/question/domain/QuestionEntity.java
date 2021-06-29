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

    @Id @GeneratedValue
    private Long id;   //question 의 id
    private Long productId; //질문할 상품id
    private String productName; //상품이름
    private Long userId;  //질문 작성한 userId
    private String content;  //질문할 내용
    private String answer; //답변


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
