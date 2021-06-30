package com.don.shopping.domains.question.service;

import com.don.shopping.domains.question.domain.QuestionEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class QuestionResponseDto {

    private Long id;
    private String productName;
    private String content;
    private String userName;
    private Long userId;
    private LocalDateTime createdDate;
    private String answer;

    @Builder
    public QuestionResponseDto(QuestionEntity question) {
        this.id = question.getId();
        this.productName = question.getProductName();
        this.userId = question.getUserId();
        this.content = question.getContent();
        this.createdDate = question.getCreatedDate();
    }
}
