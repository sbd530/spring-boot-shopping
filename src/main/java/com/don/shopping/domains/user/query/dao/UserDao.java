package com.don.shopping.domains.user.query.dao;

import com.don.shopping.domains.question.domain.QuestionEntity;
import com.don.shopping.domains.user.query.dto.MyPageRequestDto;

import java.util.List;

public interface UserDao {

    void updateUserInfo(String email, MyPageRequestDto dto);
    void modifyPassword(String email, String newPassword);
    List<QuestionEntity> findQuestionsByUserId(Long userId);
}
