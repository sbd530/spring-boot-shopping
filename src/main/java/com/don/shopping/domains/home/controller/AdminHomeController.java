package com.don.shopping.domains.home.controller;

import com.don.shopping.domains.home.service.AdminHomeService;
import com.don.shopping.domains.home.service.SummaryDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AdminHomeController {

    private final AdminHomeService homeService;

    @GetMapping("/dashboard/home")
    public SummaryDto getHomePage() {

        return homeService.getSummary();
    }


}
