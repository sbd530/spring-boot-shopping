package com.don.shopping.domains.question.domain;

import com.don.shopping.common.logging.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "question_answer")
@Getter
@Setter
@NoArgsConstructor
public class QuestionAnswerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long questionId;
    private String content;

    @Builder
    public QuestionAnswerEntity(Long questionId, String content) {
        this.questionId = questionId;
        this.content = content;
    }
}
