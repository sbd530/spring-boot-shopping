package com.don.shopping.domains.question.controller;

import com.don.shopping.domains.question.domain.QuestionEntity;
import com.don.shopping.domains.question.query.QuestionDao;
import com.don.shopping.domains.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionDao questionDao;

    //전체 질문 조회
    @GetMapping("/questions")
    public String listQuestionView(Model model){
        List<QuestionEntity> questionEntityList = questionService.findAllQuestions();
        model.addAttribute("questionLists",questionEntityList);
        return "dashboard/question/questionlist";

    }
    //질문 등록
    @GetMapping("question/add")
    public String addQuestion(Model model){
        model.addAttribute("questionForm", new QuestionForm());

        return "dashboard/question/addquestion";
    }
    //질문 등록
    @PostMapping("/question/add")
    public String addQuestionPost(QuestionForm questionForm){
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setContent(questionForm.getContent());
        questionEntity.setProductId(questionForm.getProductId());

        Long questionId = questionService.addQuestion(questionEntity);
        return "redirect:/questions";
    }
    //개별 수정 update
    @GetMapping("/question/{questionId}/edit")
    public String updateQuestionForm(@PathVariable("questionId")Long questionId,Model model){

        QuestionEntity questionEntity = questionService.findOneQuestion(questionId);
        QuestionForm form = new QuestionForm();
        form.setContent(questionEntity.getContent());
        form.setProductId(questionEntity.getProductId());
        model.addAttribute("questionForm",form);
        model.addAttribute("id",questionId);
        return "dashboard/question/questionupdate";

    }
    //개별 수정 update
    @PostMapping("/question/{questionId}/edit")
    public String updateQuestion(@PathVariable("questionId") Long questionId, @ModelAttribute("questionForm") QuestionForm questionForm){
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(questionId);
        questionEntity.setContent(questionForm.getContent());
        questionEntity.setProductId(questionForm.getProductId());

        questionService.addQuestion(questionEntity);
        return "redirect:/questions";
    }

    //개별 삭제 delete
    @GetMapping("/question/{questionId}/delete")
    public String deleteQuestion(@PathVariable("questionId") Long questionId){
        questionDao.deleteQuestionOne(questionId);
        return "redirect:/questions";
    }

}
