package com.don.shopping.domains.home.service;

import com.don.shopping.domains.home.domain.MemoryHomeRepository;
import com.don.shopping.domains.order.domain.OrderRepository;
import com.don.shopping.domains.order.query.dao.OrderDao;
import com.don.shopping.domains.product.domain.ProductRepository;
import com.don.shopping.domains.product.query.dao.ProductDao;
import com.don.shopping.domains.question.domain.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AdminHomeService {

    private final OrderRepository orderRepository;
    private final OrderDao orderDao;
    private final ProductRepository productRepository;
    private final ProductDao productDao;
    private final QuestionRepository questionRepository;
    private final MemoryHomeRepository memoryHomeRepository;


    @Transactional(readOnly = true)
    public SummaryDto getSummary() {

        Long productTotalAmount = productRepository.count();
        Long outOfStock = productDao.countOutOfStock();

        SummaryDto summaryDto = SummaryDto.builder()
                .paymentSuccess(orderDao.countPaymentSuccess())
                .canceled(orderDao.countCanceled())
                .ready(orderDao.countReady())
                .done(orderDao.countDone())
                .onSale(productTotalAmount - outOfStock)
                .outOfStock(outOfStock)
                .haveToAnswer(memoryHomeRepository.getHaveToAnswer())
                .build();

        return summaryDto;
    }


}
