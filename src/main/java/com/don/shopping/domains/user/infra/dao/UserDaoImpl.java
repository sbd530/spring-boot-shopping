package com.don.shopping.domains.user.infra.dao;

import com.don.shopping.domains.user.domain.QUserEntity;
import com.don.shopping.domains.user.query.dao.UserDao;
import com.don.shopping.domains.user.query.dto.MyPageRequestDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserDaoImpl implements UserDao {

    private final JPAQueryFactory query;
    private final QUserEntity user = QUserEntity.userEntity;

    public UserDaoImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public void updateUserInfo(Long userId, MyPageRequestDto dto) {

        query.update(user)
                .where(user.id.eq(userId))
                .set(user.name, dto.getName())
                .set(user.phoneNumber, dto.getPhoneNumber())
                .set(user.address, dto.getAddress())
                .execute();
    }

    @Override
    public void modifyPassword(Long userId, String newPassword) {

        query.update(user)
                .where(user.id.eq(userId))
                .set(user.password, newPassword)
                .execute();
    }
}
