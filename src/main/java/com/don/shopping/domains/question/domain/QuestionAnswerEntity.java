package com.don.shopping.domains.question.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "question_answer")
@Getter
@Setter //setter은 지우는게 좋음
@NoArgsConstructor
public class QuestionAnswerEntity {

    @Id
    @GeneratedValue
    private Long id;   //question_answer 의 id

    private String name; //답변한 사람 (관리자)

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private QuestionEntity questionEntity;*/

    private Long questionId; //답변할 질문 id

    private String content;  //답변할 내용

    private LocalDateTime questionAnswerTime; //답변한 작성시간

    @PrePersist
    protected void onReviewTimeCreate(){
        this.questionAnswerTime = LocalDateTime.now();
    }
    @PreUpdate
    protected  void unReviewTimeUpdate(){
        this.questionAnswerTime = LocalDateTime.now();
    }


    @Builder
    public QuestionAnswerEntity(Long id, String name, Long questionId, String content, LocalDateTime questionAnswerTime) {
        this.id = id;
        this.name = name;
        this.questionId = questionId;
        this.content = content;
        this.questionAnswerTime = questionAnswerTime;
    }
}
