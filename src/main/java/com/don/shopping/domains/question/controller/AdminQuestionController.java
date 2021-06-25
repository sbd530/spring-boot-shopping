package com.don.shopping.domains.question.controller;

import com.don.shopping.domains.question.domain.QuestionAnswerEntity;
import com.don.shopping.domains.question.query.QuestionAnswerDao;
import com.don.shopping.domains.question.query.QuestionDao;
import com.don.shopping.domains.question.service.AdminAnswerDto;
import com.don.shopping.domains.question.service.AdminQuestionDto;
import com.don.shopping.domains.question.service.QuestionAnswerService;
import com.don.shopping.domains.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AdminQuestionController {

    private final QuestionService questionService;
    private final QuestionAnswerService questionAnswerService;
    private final QuestionAnswerDao questionAnswerDao;
    private final QuestionDao questionDao;

    @GetMapping("/dashboard/questions")
    public List<AdminQuestionDto> getQuestions() {
        return questionService
                .findAllQuestions()
                .stream()
                .map(AdminQuestionDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/dashboard/questions/answer")
    public ResponseEntity addAnswerToTheQuestion(@RequestBody AdminAnswerDto adminAnswerDto) {
        QuestionAnswerEntity answer = QuestionAnswerEntity.builder()
                .content(adminAnswerDto.getContent())
                .questionId(adminAnswerDto.getQuestionId())
                .build();
        questionAnswerService.addAnswer(answer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/dashboard/questions")
    public ResponseEntity deleteQuestion(@RequestBody AdminAnswerDto adminAnswerDto){
        Long questionId = adminAnswerDto.getQuestionId();
        questionAnswerDao.deleteQuestionAnswer(questionId);
        questionDao.deleteQuestionOne(questionId);
        return ResponseEntity.ok().build();
    }


}
