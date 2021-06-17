package com.don.shopping.domains.product.controller;


import com.don.shopping.domains.product.service.ProductImageService;
import com.don.shopping.domains.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
public class ProductImageController {
    private final ProductService productService;
    private final ProductImageService productImageService;

    @GetMapping(
            value = "/products/{productId}/images/{usage}",
            produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<byte[]> viewImage(@PathVariable Long productId, @PathVariable String usage) throws IOException {

        String saveFileName = productImageService.getFileNameForProductPageByUsage(productId,usage);
        InputStream productImageStream = new FileInputStream("C://productImages/" + saveFileName);
        byte[] productImageByteArray = IOUtils.toByteArray(productImageStream);
        productImageStream.close();

        return new ResponseEntity<byte[]>(productImageByteArray, HttpStatus.OK);
    }


}
