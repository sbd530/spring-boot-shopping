package com.don.shopping.domains.product.query.dto;

import com.don.shopping.domains.product.domain.ProductImageVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateProductDto {

    private String productName;
    private String productInfo;
    private int rprice;
    private int dprice;
    private int stock;

    public UpdateProductDto (ProductImageVO productImageVO) {
        this.productName = productImageVO.getProductName();
        this.productInfo = productImageVO.getProductInfo();
        this.rprice = productImageVO.getRprice();
        this.dprice = productImageVO.getDprice();
        this.stock = productImageVO.getStock();
    }
}
