package com.don.shopping.domains.product.domain;

import lombok.Getter;

@Getter
public enum ImageUsage {
    THUMBNAIL1("THUMBNAIL1"),THUMBNAIL2("THUMBNAIL2"),CONTENTS1("CONTENTS1"),CONTENTS2("CONTENTS2");

    String imageUsage;

    ImageUsage(String imageUsage) {this.imageUsage = imageUsage;}
}
