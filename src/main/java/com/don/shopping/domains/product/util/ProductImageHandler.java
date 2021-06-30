package com.don.shopping.domains.product.util;

import com.don.shopping.domains.product.domain.ImageUsage;
import com.don.shopping.domains.product.domain.ProductEntity;
import com.don.shopping.domains.product.domain.ProductImageEntity;
import com.don.shopping.domains.product.service.ProductImageDto;
import com.don.shopping.domains.product.service.ProductImageService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class ProductImageHandler {

    private final ProductImageService productImageService;

    public ProductImageHandler(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    public List<ProductImageEntity> parseFileInfo(
            ProductEntity productEntity, //product에 존재하는 파일인지 확인
            List<MultipartFile> multipartFiles
    ) throws Exception {
        //반환할 파일 리스트
        List<ProductImageEntity> fileList = new ArrayList<>();

        //전달되어 온 파일이 존재하는 경우
        if(!CollectionUtils.isEmpty(multipartFiles)) {
            //파일명을 업로드한 날짜로 변환하여 저장
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd");
            String currenDate = now.format(dateTimeFormatter);

            //파일을 저장할 세부 경로 지정
            String path = "C:" + File.separator + "productImages";
            File file = new File(path);

            //디렉터리가 존재하지 않을 경우
            if(!file.exists()) {
                boolean wasSuccessful = file.mkdirs();

            //디렉터리 생성에 실패했을 경우
            if(!wasSuccessful)
                System.out.println("file : was not successfully upload!");
            }

            //다중 파일 처리
            for(int i=0;i<=3;i++) {
                MultipartFile multipartFile = multipartFiles.get(i);
                //파일의 확장자 추출
                String fileExt;
                String contentType="";
                try {
                    contentType = multipartFile.getContentType();
                } catch (NullPointerException e) {

                }

                //확장자명이 존재하지 않을 경우 처리x
                if(ObjectUtils.isEmpty(contentType)) {
                    continue;
                }else {//확장자가 jpeg, png, gif인 파일들만 받아서 처리
                    if (contentType.contains("image/jpeg")) {
                        fileExt = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        fileExt = ".png";
                    } else {
                        continue;
                    }
                }
                //파일명 중복 피하기 위해 나노초 지정
                String saveFileName = String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS",
                        Calendar.getInstance()) + System.nanoTime() + fileExt;

                ImageUsage imageUsage;

                switch (i) {
                    case 0 : imageUsage = ImageUsage.THUMBNAIL1;break;
                    case 1 : imageUsage = ImageUsage.THUMBNAIL2;break;
                    case 2 : imageUsage = ImageUsage.CONTENTS1;break;
                    default : imageUsage = ImageUsage.CONTENTS2;
                }

                //파일 DTO 생성
                ProductImageDto productImageDto = ProductImageDto.builder()
                        .originalFileName(multipartFile.getOriginalFilename())
                        .saveFileName(saveFileName)
                        .imageUsage(imageUsage)
                        .fileSize(multipartFile.getSize())
                        .build();

                //파일 DTO 이용하여 ProductImage 엔티티 생성
                ProductImageEntity productImageEntity = new ProductImageEntity(
                        productImageDto.getOriginalFileName(),
                        productImageDto.getSaveFileName(),
                        productImageDto.getImageUsage(),
                        productImageDto.getFileSize()
                );

                //product에 존재 x > 게시글에 사진 정보 저장
                if(productEntity.getId()!=null) {
                    productImageEntity.saveProductEntity(productEntity);
                }

                //생성 후 리스트에 추가
                fileList.add(productImageEntity);

                //업로드 한 파일 데이터를 지정한 파일에 저장
                file = new File(path + File.separator + saveFileName);
                multipartFile.transferTo(file);

                //파일 권한 설정(쓰기,읽기)
                file.setWritable(true);
                file.setReadable(true);

            }
        }
        return fileList;
    }

    public void doFileDelete(String saveFileName) {
        try {
            String filePath = "C:" + File.separator + "productImages" + File.separator + saveFileName;
            File f = new File(filePath);
            if(f.exists()) {
                f.delete();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

