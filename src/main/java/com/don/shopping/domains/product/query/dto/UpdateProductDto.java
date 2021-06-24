package com.don.shopping.domains.product.query.dto;

import com.don.shopping.domains.product.domain.ProductImageVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class UpdateProductDto {

    private String productName;
    private String productInfo;
    private Integer rprice;
    private Integer dprice;
    private Integer stock;
    private MultipartFile file1;
    private MultipartFile file2;
    private MultipartFile file3;
    private MultipartFile file4;
    private Long imageId1;
    private Long imageId2;
    private Long imageId3;
    private Long imageId4;

    public UpdateProductDto (ProductImageVO productImageVO) {
        this.productName = productImageVO.getProductName();
        this.productInfo = productImageVO.getProductInfo();
        this.rprice = productImageVO.getRprice();
        this.dprice = productImageVO.getDprice();
        this.stock = productImageVO.getStock();
        this.file1 = productImageVO.getFile1();
        this.file2 = productImageVO.getFile2();
        this.file3 = productImageVO.getFile3();
        this.file4 = productImageVO.getFile4();
        this.imageId1 = productImageVO.getImageId1();
        this.imageId2 = productImageVO.getImageId2();
        this.imageId3 = productImageVO.getImageId3();
        this.imageId4 =productImageVO.getImageId4();
    }
}
