package com.don.shopping.domains.question.service;

import com.don.shopping.domains.home.domain.MemoryHomeRepository;
import com.don.shopping.domains.question.domain.QuestionAnswerEntity;
import com.don.shopping.domains.question.domain.QuestionAnswerRepository;
import com.don.shopping.domains.question.query.QuestionAnswerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionAnswerService {

    private final QuestionAnswerRepository questionAnswerRepository;
    private final QuestionAnswerDao questionAnswerDao;
    private final MemoryHomeRepository memoryHomeRepository; // To control haveToAnswer field @윤병돈

    //질문에 대한 답변 등록
    @Transactional
    public Long addAnswer(QuestionAnswerEntity questionAnswerEntity){
        questionAnswerRepository.save(questionAnswerEntity);
        //답변해야할 질문을 제거합니다 @윤병돈
        Long questionId = questionAnswerEntity.getQuestionId();
        memoryHomeRepository.removeQuestionToAnswer(questionId);
        return questionAnswerEntity.getId();
    }

    //그 질문에 대한 답변 전체 조회
    @Transactional(readOnly = true)
    public QuestionAnswerEntity findAnswerByQuestion(Long questionId){
        return questionAnswerDao.findAnswerByQuestionId(questionId);
    }


}
