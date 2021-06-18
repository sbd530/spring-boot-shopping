package com.don.shopping.domains.question.service;

import com.don.shopping.domains.question.domain.QuestionAnswerEntity;
import com.don.shopping.domains.question.domain.QuestionAnswerRepository;
import com.don.shopping.domains.question.query.QuestionAnswerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionAnswerService {

    private final QuestionAnswerRepository questionAnswerRepository;
    private final QuestionAnswerDao questionAnswerDao;

    //질문에 대한 답변 등록
    @Transactional
    public Long addAnswer(QuestionAnswerEntity questionAnswerEntity){
        questionAnswerRepository.save(questionAnswerEntity);
        return questionAnswerEntity.getId();
    }

    //그 질문에 대한 답변 전체 조회
    @Transactional(readOnly = true)
    public List<QuestionAnswerEntity> findAllReviewsByQuestion(Long questionId){
        List<QuestionAnswerEntity> questionAnswerEntity =
                questionAnswerDao.findQuestionByQuestionId(questionId);

        return questionAnswerEntity;
    }


}
