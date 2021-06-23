package com.don.shopping.domains.home.controller;

import com.don.shopping.domains.home.service.AdminHomeService;
import com.don.shopping.domains.home.service.SummaryDto;
import com.don.shopping.util.RedisUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AdminHomeController {

    private final AdminHomeService homeService;
    private final RedisUtil redis;

    @GetMapping("/dashboard/home")
    public SummaryDto getHomePage(@RequestBody String key) {

        SummaryDto summaryDto = null;


        return summaryDto;
    }


}
