package com.don.shopping.domains.home.controller;

import com.don.shopping.domains.home.service.AdminHomeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AdminHomeController {

    private final AdminHomeService homeService;

    @GetMapping("/dashboard/home")
    public ResponseEntity getHomePage() {



        return ResponseEntity.ok().build();
    }


}
