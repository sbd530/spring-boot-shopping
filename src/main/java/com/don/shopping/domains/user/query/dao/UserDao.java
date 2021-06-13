package com.don.shopping.domains.user.query.dao;

import com.don.shopping.domains.user.query.dto.ChangePasswordDto;
import com.don.shopping.domains.user.query.dto.MyPageRequestDto;

public interface UserDao {

    void updateUserInfo(Long userId, MyPageRequestDto dto);
    void modifyPassword(Long userId, String newPassword);
}
