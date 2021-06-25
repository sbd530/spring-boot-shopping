package com.don.shopping.domains.user.service;

import com.don.shopping.domains.question.domain.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MyQuestionDto {

    private LocalDateTime questionTime;
    private Long productId;
    private String content;

    public MyQuestionDto(QuestionEntity question) {
        this.questionTime = question.getCreatedDate();
        this.productId = question.getProductId();
        this.content = question.getContent();
    }
}
