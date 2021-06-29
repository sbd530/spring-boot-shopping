package com.don.shopping.util;

import java.util.Map;

public interface JwtService {
    <T> String create(String key, T data, String subject);
    Map<String, Object> get(String key);
    Long getUserId();
    boolean isUsable(String jwt);
}
