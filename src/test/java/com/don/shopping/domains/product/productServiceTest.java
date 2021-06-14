package com.don.shopping.domains.product;

import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.domain.ProductRepository;
import com.don.shopping.domains.product.query.dto.ProductImageDto;
import com.don.shopping.domains.product.query.dto.ProductRequestDto;
import com.don.shopping.domains.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class productServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;


    @Test
    public void 상품리스트() throws Exception {

        List<ProductEntity> product = productRepository.findAll();
        System.out.println(product);

    }


}
