package com.don.shopping.domains.user.controller;

import com.don.shopping.domains.user.service.AdminUserDto;
import com.don.shopping.domains.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AdminUserController {

    private final UserService userService;

    @GetMapping("/dashboard/users")
    public List<AdminUserDto> getAdminUserDtoList(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        List<AdminUserDto> userList = userService.getUserList(pageable);
        return userList;
    }


}
