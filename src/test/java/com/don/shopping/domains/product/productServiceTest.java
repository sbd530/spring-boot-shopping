package com.don.shopping.domains.product;

import com.don.shopping.domains.product.domain.ImageUsage;
import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.domain.ProductImageVO;
import com.don.shopping.domains.product.domain.ProductRepository;
import com.don.shopping.domains.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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

    @Test
    public void 이넘() throws Exception {
        System.out.println(ImageUsage.valueOf("THUMBNAIL1").getClass());
    }

    @Test
    public void 파일() throws Exception {
        ProductImageVO productImageVO = new ProductImageVO();
        System.out.println(productImageVO.getFiles().getClass());
    }



}
