package com.don.shopping.domains.question.controller;

import com.don.shopping.domains.question.domain.QuestionAnswerEntity;
import com.don.shopping.domains.question.query.QuestionAnswerDao;
import com.don.shopping.domains.question.service.QuestionAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AnswerController {

    private final QuestionAnswerService questionAnswerService;
    private final QuestionAnswerDao questionAnswerDao;

    //댓글창 CUSTOMER
    @GetMapping("question/{questionId}/addanswerCustom")
    public String addAnswer2(@PathVariable("questionId")Long questionId,Model model){
        model.addAttribute("answerForm", new AnswerForm());
        QuestionAnswerEntity questionAnswerEntity = questionAnswerDao.findAnswerByQuestionId(questionId);
        model.addAttribute("answerList",questionAnswerEntity);
        model.addAttribute("questionId",questionId);
        return "customer/answer/answerlist";
    }

    //댓글등록창 ADMIN
    @GetMapping("question/{questionId}/addanswer")
    public String addAnswer(@PathVariable("questionId")Long questionId,Model model){
        model.addAttribute("answerForm", new AnswerForm());
        QuestionAnswerEntity questionAnswerEntity = questionAnswerDao.findAnswerByQuestionId(questionId); //리스트를 AdminQuestionDto
        model.addAttribute("answerList",questionAnswerEntity);
        model.addAttribute("questionId",questionId);
        return "dashboard/answer/addanswer";
    }

    // 댓글등록창
    @PostMapping("question/{questionId}/addanswer")
    public String addAnswerPost(@PathVariable("questionId")Long questionId, Model model, @ModelAttribute("answerForm") AnswerForm answerForm){

        QuestionAnswerEntity questionAnswerEntity = new QuestionAnswerEntity();
        questionAnswerEntity.setQuestionId(questionId);
        questionAnswerEntity.setContent(answerForm.getContent());
        questionAnswerService.addAnswer(questionAnswerEntity);


        return "redirect:/question/"+ questionId+"/addanswer";
    }

    //댓글 개별 삭제 delete
    @GetMapping("question/{questionId}/delete/{answerId}")
    public String deleteAnswer(@PathVariable("questionId") Long questionId, @PathVariable("answerId") Long answerId){
        questionAnswerDao.deleteQuestionAnswerOne(answerId);
        return "redirect:/question/"+ questionId+"/addanswer";
    }


}
