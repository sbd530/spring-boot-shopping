package com.don.shopping.domains.question.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswerEntity, Long> {
}
