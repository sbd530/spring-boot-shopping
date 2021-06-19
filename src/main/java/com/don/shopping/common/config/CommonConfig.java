package com.don.shopping.common.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * JPA 의 Auditing 을 설정합니다.
 * QueryDSL 에 필요한 빈을 등록합니다.
 * 작성자 : 윤병돈
 */
@Configuration
@EnableJpaAuditing
public class CommonConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
