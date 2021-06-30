package com.don.shopping.domains.question.service;

import com.don.shopping.domains.question.domain.QuestionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class AdminQuestionDto {

    private Long id;
    private Long productId;
    private String productName;
    private Long userId;
    private String userName;
    private String content;
    private String questionTime;
    private String answer;

    public AdminQuestionDto(QuestionEntity question) {
        this.id = question.getId();
        this.productId = question.getProductId();
        this.productName = question.getProductName();
        this.answer = question.getAnswer();
        this.userId = question.getUserId();
        this.userName = question.getUserName();
        this.content = question.getContent();
        this.questionTime = question
                .getCreatedDate()
                .format(DateTimeFormatter.ofPattern("yy-MM-dd"));

    }
}
