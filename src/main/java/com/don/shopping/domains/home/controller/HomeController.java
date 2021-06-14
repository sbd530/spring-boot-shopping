package com.don.shopping.domains.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHomePage() {
        return "customer/main/home";
    }

    @PostMapping("/home")
    public String getHomePage2() {
        return "customer/main/home";
    }
}
