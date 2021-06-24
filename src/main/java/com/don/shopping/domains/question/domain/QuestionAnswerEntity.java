package com.don.shopping.domains.question.domain;

import com.don.shopping.common.logging.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "question_answer")
@Getter
@Setter //setter은 지우는게 좋음
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
