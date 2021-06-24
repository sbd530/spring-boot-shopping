package com.don.shopping.domains.question.service;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AdminAnswerDto {

    @NotNull
    private Long questionId;
    private String content;

}
