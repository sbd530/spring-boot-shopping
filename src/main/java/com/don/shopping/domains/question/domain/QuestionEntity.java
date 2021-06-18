package com.don.shopping.domains.question.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Getter @Setter //setter은 지우는게 좋음
@NoArgsConstructor
public class QuestionEntity {

    @Id @GeneratedValue
    private Long id;   //question 의 id

    private Long productId; //질문할 상품id

    private Long userId;  //질문 작성한 userId

    private String content;  //질문할 내용

    private LocalDateTime questionTime; //질문문 작성시간

    @PrePersist
    protected void onReviewTimeCreate(){
        this.questionTime = LocalDateTime.now();
    }
    @PreUpdate
    protected  void unReviewTimeUpdate(){
        this.questionTime = LocalDateTime.now();
    }



    @OneToMany
    private List<QuestionAnswerEntity> questionAnswerEntity = new ArrayList<>();

    @Builder
    public QuestionEntity(Long id, Long productId, Long userId, String content, LocalDateTime questionTime, List<QuestionAnswerEntity> questionAnswerEntity) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.content = content;
        this.questionTime = questionTime;
        this.questionAnswerEntity = questionAnswerEntity;
    }
}
