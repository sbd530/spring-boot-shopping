package com.don.shopping.domains.question.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminAnswerDto {

    @NotNull
    private Long questionId;
    private String content;

}
