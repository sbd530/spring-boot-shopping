package com.don.shopping.domains.user.query.dao;

import com.don.shopping.domains.question.domain.QuestionEntity;
import com.don.shopping.domains.user.domain.UserEntity;
import com.don.shopping.domains.user.query.dto.MyPageRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UserDao {

    void updateUserInfo(String email, MyPageRequestDto dto);
    void modifyPassword(String email, String newPassword);
    List<QuestionEntity> findQuestionsByUserId(Long userId);

    Page<UserEntity> findUserList(Pageable pageable);
}
