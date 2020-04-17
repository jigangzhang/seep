package com.god.seep.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES CBC模式加解密
 */
public class EncryptUtil {
    public static final String key = "_zone_@2019.code";            // 密钥
    private static final String iv = "luanlaibuyaomofa";            // 初始化向量

    private static final String ALGORITHM_NAME = "AES";            // 使用 AES 加密
    private static final String DEFAULT_MODE = "CBC";            // 分组密码工作模式
    private static final String DEFAULT_PADDING = "PKCS5Padding";   // 填充模式

    private static final String DEFAULT_ENCODE = "UTF-8";          // 文本编码

    private static final String DEFAULT_CHIPHER_INSTANCE = ALGORITHM_NAME + "/" + DEFAULT_MODE + "/" + DEFAULT_PADDING;  // AES/CBC/PKCS5Padding
    private static String CipherMode = DEFAULT_CHIPHER_INSTANCE;

    private static IvParameterSpec getIvParameterSpec(String iv) {
        final int MAX_SIZE = 16;
        if (iv == null) {
            iv = "";
        }
        byte[] data = new byte[MAX_SIZE];
        byte[] data_str = iv.getBytes();
        for (int i = 0, len = data_str.length; i < MAX_SIZE; ++i) {
            data[i] = (i < len) ? data_str[i] : 0;
        }
        return new IvParameterSpec(data);
    }

    public static String encode(String key, String data) {
        try {
            Cipher cipher = Cipher.getInstance(CipherMode);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), ALGORITHM_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, getIvParameterSpec(iv));
            byte[] bytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decode(String key, String data) {
        byte[] bytes = Base64.getDecoder().decode(data);
        try {
            Cipher cipher = Cipher.getInstance(CipherMode);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), ALGORITHM_NAME);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, getIvParameterSpec(iv));
            byte[] aFinal = cipher.doFinal(bytes);
            return new String(aFinal, DEFAULT_ENCODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
