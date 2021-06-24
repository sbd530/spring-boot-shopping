package com.don.shopping.domains.question.service;

import com.don.shopping.domains.home.domain.MemoryHomeRepository;
import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.domain.ProductRepository;
import com.don.shopping.domains.question.domain.QuestionEntity;
import com.don.shopping.domains.question.domain.QuestionRepository;
import com.don.shopping.domains.question.query.QuestionDao;
import com.don.shopping.domains.review.domain.ReviewEntity;
import com.don.shopping.domains.user.domain.UserEntity;
import com.don.shopping.domains.user.domain.UserRepository;
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
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final MemoryHomeRepository memoryHomeRepository;

    //질문 등록
    @Transactional
    public Long addQuestion(QuestionEntity questionEntity){
        String productName = productRepository
                .findById(questionEntity.getProductId())
                .orElseThrow(() -> new IllegalArgumentException())
                .getProductName();
        questionEntity.setProductName(productName);
        Long questionId = questionRepository.save(questionEntity).getId();
        // 답변해야할 질문을 추가합니다. @윤병돈
        memoryHomeRepository.saveQuestionToAnswer(questionId);
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
    @Transactional
    public String getUserName(Long userId){
        UserEntity userEntity = userRepository.getOne(userId);
        return userEntity.getName();
    }

    @Transactional
    public String getProductName(Long productId){
        ProductEntity productEntity =  productRepository.getOne(productId);
        return productEntity.getProductName();
    }



}
