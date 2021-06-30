package com.don.shopping.domains.question.controller;

import com.don.shopping.domains.question.domain.QuestionAnswerEntity;
import com.don.shopping.domains.question.domain.QuestionEntity;
import com.don.shopping.domains.question.query.QuestionAnswerDao;
import com.don.shopping.domains.question.query.QuestionDao;
import com.don.shopping.domains.question.service.*;
import com.don.shopping.domains.user.domain.UserEntity;
import com.don.shopping.domains.user.service.UserService;
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
    private final UserService userService;

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

        QuestionEntity questionEntity = questionService.findOneQuestion(adminAnswerDto.getQuestionId());
        questionEntity.setAnswer(adminAnswerDto.getContent()); //setter로 answer넣기

        UserEntity userEntity = userService.findUserById(questionEntity.getUserId());
        System.out.println("questionEntity.getUserId()questionEntity.getUserId()"+questionEntity.getUserId());
        questionEntity.setUserName(userEntity.getName());//setter로 user넣기

        questionService.addQuestion(questionEntity);
        QuestionAnswerEntity answer = QuestionAnswerEntity.builder()
                .content(adminAnswerDto.getContent())
                .questionId(adminAnswerDto.getQuestionId())
                .build();
        questionAnswerService.addAnswer(answer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/dashboard/questions/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable Long questionId){
        questionAnswerDao.deleteQuestionAnswer(questionId);
        questionDao.deleteQuestionOne(questionId);
        return ResponseEntity.ok().build();
    }


}
