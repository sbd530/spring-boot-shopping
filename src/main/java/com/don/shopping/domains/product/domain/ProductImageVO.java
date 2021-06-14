package com.don.shopping.domains.product.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductImageVO {
    private String productname;
    private String productinfo;
    private int rprice;
    private int dprice;
    private int stock;
    private List<MultipartFile> productimages;
}
