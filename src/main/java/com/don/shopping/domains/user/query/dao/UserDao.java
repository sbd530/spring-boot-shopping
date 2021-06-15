package com.don.shopping.domains.user.query.dao;

import com.don.shopping.domains.user.query.dto.MyPageRequestDto;

public interface UserDao {

    void updateUserInfo(String email, MyPageRequestDto dto);
    void modifyPassword(String email, String newPassword);
}
