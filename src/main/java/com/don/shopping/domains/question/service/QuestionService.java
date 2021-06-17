package com.don.shopping.domains.question.service;

import com.don.shopping.domains.question.domain.QuestionEntity;
import com.don.shopping.domains.question.domain.QuestionRepository;
import com.don.shopping.domains.question.query.QuestionDao;
import com.don.shopping.domains.review.domain.ReviewEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionDao questionDao;

    //질문 등록
    @Transactional
    public Long addQuestion(QuestionEntity questionEntity){
        questionRepository.save(questionEntity);
        return questionEntity.getId();
    }

    //리뷰 전체 조회
    @Transactional(readOnly = true) // 성능이 최적화된다 읽기 전용이니까 리소스를 많이 안씀(읽기에는 가급적이면 readonly)
    public List<QuestionEntity> findAllQuestions(){
        return questionRepository.findAll();
    }

    //리뷰 하나 조회(수정창)
    @Transactional(readOnly = true)
    public QuestionEntity findOneQuestion(Long reviewId){
        return questionRepository.findById(reviewId).orElseThrow(()
                -> new IllegalArgumentException("해당 질문이 없습니다."));
    }

    //해당 상품의 모든 질문
    @Transactional(readOnly = true)
    public List<QuestionEntity> findQuestionsByProductId(Long productId){
        return questionDao.findQuestionsByProductId(productId);

    }



}
