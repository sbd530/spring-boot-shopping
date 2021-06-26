package com.don.shopping.domains.question.domain;

import com.don.shopping.domains.question.service.QuestionResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findAllByProductId(Long productId);

}
