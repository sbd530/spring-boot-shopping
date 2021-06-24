package com.don.shopping.domains.question.controller;

import com.don.shopping.domains.question.domain.QuestionAnswerEntity;
import com.don.shopping.domains.question.service.AdminAnswerDto;
import com.don.shopping.domains.question.service.AdminQuestionDto;
import com.don.shopping.domains.question.service.QuestionAnswerService;
import com.don.shopping.domains.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AdminQuestionController {

    private final QuestionService questionService;
    private final QuestionAnswerService questionAnswerService;

    @GetMapping("/dashboard/questions")
    public List<AdminQuestionDto> getQuestions() {
        return questionService
                .findAllQuestions()
                .stream()
                .map(AdminQuestionDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/dashboard/questions/answer")
    public ResponseEntity addAnswerToTheQuestion(AdminAnswerDto adminAnswerDto) {
        QuestionAnswerEntity answer = QuestionAnswerEntity.builder()
                .content(adminAnswerDto.getContent())
                .questionId(adminAnswerDto.getQuestionId())
                .build();
        questionAnswerService.addAnswer(answer);
        return ResponseEntity.ok().build();
    }

}
