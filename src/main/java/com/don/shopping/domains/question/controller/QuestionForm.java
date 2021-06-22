package com.don.shopping.domains.question.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class QuestionForm {

    @NotBlank
    private String questionContent;
    private Long questionProductId;

}
