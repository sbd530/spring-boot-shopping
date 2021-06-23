package com.don.shopping.domains.home.service;

import com.don.shopping.domains.order.domain.OrderRepository;
import com.don.shopping.domains.product.domain.ProductRepository;
import com.don.shopping.domains.question.domain.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminHomeService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final QuestionRepository questionRepository;




}
