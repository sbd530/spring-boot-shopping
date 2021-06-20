package com.don.shopping.domains.user.infra.dao;

import com.don.shopping.domains.question.domain.QQuestionEntity;
import com.don.shopping.domains.question.domain.QuestionEntity;
import com.don.shopping.domains.user.domain.QUserEntity;
import com.don.shopping.domains.user.query.dao.UserDao;
import com.don.shopping.domains.user.query.dto.MyPageRequestDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final JPAQueryFactory query;
    private final QUserEntity user = QUserEntity.userEntity;
    private final QQuestionEntity question = QQuestionEntity.questionEntity;

    public UserDaoImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public void updateUserInfo(String email, MyPageRequestDto dto) {

        query.update(user)
                .where(user.email.eq(email))
                .set(user.name, dto.getName())
                .set(user.phoneNumber.phoneNumber1, dto.getPhoneNumber().getPhoneNumber1())
                .set(user.phoneNumber.phoneNumber2, dto.getPhoneNumber().getPhoneNumber2())
                .set(user.phoneNumber.phoneNumber3, dto.getPhoneNumber().getPhoneNumber3())
                .set(user.address.postNumber, dto.getAddress().getPostNumber())
                .set(user.address.address1, dto.getAddress().getAddress1())
                .set(user.address.address2, dto.getAddress().getAddress2())
                .execute();
    }

    @Override
    public void modifyPassword(String email, String newPassword) {

        query.update(user)
                .where(user.email.eq(email))
                .set(user.password, newPassword)
                .execute();
    }

    @Override
    public List<QuestionEntity> findQuestionsByUserId(Long userId) {
        List<QuestionEntity> myQuestions = query.select(question)
                .from(question)
                .where(question.userId.eq(userId))
                .fetch();
        return myQuestions;
    }
}
