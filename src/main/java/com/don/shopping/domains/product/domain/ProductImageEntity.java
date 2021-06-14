package com.don.shopping.domains.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "productimage")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(
        name = "productimage_seq_generator",
        sequenceName = "productimage_seq", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
public class ProductImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productimage_seq_generator")
    private Long id;

    private String originalfilename;

    @Enumerated(EnumType.STRING)
    private ImageUsage imageUsage;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private String filePath;    //파일 저장 경로

    private Long fileSize;

    @Builder
    public ProductImageEntity(String originalfilename, ImageUsage imageUsage, String filePath, Long fileSize) {
        this.originalfilename = originalfilename;
        this.imageUsage = imageUsage;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    //ProductEntity 정보 저장
    public void saveProductEntity(ProductEntity productEntity) {
        this.product = productEntity;

        //상품에 현재 파일이 존재하지 않는다면
        if(!productEntity.getProductImages().contains(this)) {
            //파일 추가
            productEntity.getProductImages().add(this);
        }
    }
}

