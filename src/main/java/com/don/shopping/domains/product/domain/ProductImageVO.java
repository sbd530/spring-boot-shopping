package com.don.shopping.domains.product.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Data
public class ProductImageVO {
    private String productName;
    private String productInfo;
    private int rprice;
    private int dprice;
    private int stock;
    private MultipartFile file1;
    private MultipartFile file2;
    private MultipartFile file3;
    private MultipartFile file4;

    public List<MultipartFile> getFiles() {

        List<MultipartFile> files = new ArrayList<>();

        files.add(this.file1);
        files.add(this.file2);
        files.add(this.file3);
        files.add(this.file4);

        return files;
    }

}
