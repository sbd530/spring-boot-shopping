package com.don.shopping.domains.user.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryCodeRepository {

    private static Map<String, String> codeRepository = new HashMap<>();

    public void saveCode(String email, String code) {
        codeRepository.put(email, code);
    }

    public boolean isValidCode(String email, String inputCode) {
        String code;
        boolean check = false;
        try {
            code = codeRepository.get(email);
            if(code.equals(inputCode)) {
                codeRepository.remove(email);
                return true;
            }else{return false;}
        } catch (NullPointerException | ClassCastException exception) {
            return false;
        }

    }



}
