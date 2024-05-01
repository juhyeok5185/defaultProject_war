package com.ein.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Slf4j
public class AES256Utils {

    private static final String alg = "AES/CBC/PKCS5Padding";
    private static final String key = "#HyPeRwIsE1HyPeRwIsE2HyPeRwIsE3#";
    private static final String iv = key.substring(0, 16); // 16byte


    /**
     * 데이터 암호화
     * @param value 데이터
     * @return 암호화된 데이터
     */
    public static String encrypt(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }

        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
            byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            log.debug(e.getMessage());
        }

        return value;
    }

    /**
     * 데이터 복호화
     * @param value 데이터
     * @return 복호화된 데이터
     */
    public static String decrypt(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }

        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
            byte[] decodedBytes = Base64.getDecoder().decode(value);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, "UTF-8");

        } catch (Exception e) {
            log.debug(e.getMessage());
        }

        return value;
    }

}
