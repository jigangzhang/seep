package com.god.seep.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {
    private static final String ALGORITHM = "RSA";
    private static final int KeySize = 1024;

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
        generator.initialize(KeySize);
        return generator.generateKeyPair();
    }

    public static PrivateKey getPrivateKey(KeyPair keyPair) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        PrivateKey key = keyPair.getPrivate();
//        byte[] encoded = key.getEncoded();
//        System.out.println("私钥--before base64 -- " + new String(encoded));
//        String privateKey = Base64.encodeBase64String(encoded);
//        System.out.println("私钥--after base64 -- " + privateKey);
        String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAK15wH9iBHqbW1SjZ9OPUg/oVl1U3dDJ/RtNMXjDWrxWqvPFous6ChkGMQtAoBkB/Gdgvnmhv049TxfKKIhpJTVvqnUNp/4lMwy3BehbzeMjV/aqFUPtMzIPxrUhhO+VaSfQ+f+xgeh6kczutNAaxmzTjtnKobEevp/dr3krC1arAgMBAAECgYEAgQT6XEMkHaQKCyr0/22qlNYx1XxW9QAlVPbWgoCYrVawElm9T8F83RuzwjCwYakOdjyHraLUy3Xhq0nZV4sL9ja8sh7hT5xo6kWpos66tqe4tofgZ6YPXpCnOLSLJtx5cjxiZzttA3pwjew4Tip1i/vyWTi7bxuRw0RVud3tgQECQQDTZ3C9qXtQgKLenw8Rmc1K4ZhPqJPt+yVdROoUYBJUUR9U7fD1ozVxtsR2SKfeAxMUquvmnN79vwi7DbUPW9vBAkEA0hIKlfSqfGqHbtApjRuXsoDRqovd4JKVMJyysXE7y3nK9jrIWyUoSUyagegbEku3BBHhH0/lU1HjF1233Km9awJANTBcbEu9C/chg1OPrgT9Yu65rKkkdwbuUrYpEvFNC0vRz0OCkbr7kl3CtmP3YGNzpIqQidhEPqGW58E+IdwhwQJBAKZ273aHDDZNQIcaY2LTM7GbDQqFjUwxPBHqilu8LDMAj0nxqOU5G6SCFInOwO0NnLiX5nommMYexMK2XEV7KIMCQQDQvQRho6LZgwmYlYo30jmvwchks1P3bbiS1BBBHq4jJVSy7t+LK0gVhsGxRFmb8ECJjvz2Ou35aXlQwigH3Phm";
        KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
        byte[] decode = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decode);
        return factory.generatePrivate(keySpec);
    }

    public static PublicKey getPublicKey(KeyPair keyPair) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        PublicKey key = keyPair.getPublic();
//        byte[] encoded = key.getEncoded();
//        System.out.println("公钥--before base64 -- " + new String(encoded));
//        String pubKey = Base64.encodeBase64String(encoded);
//        System.out.println("公钥--after base64 -- " + pubKey);

        String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtecB/YgR6m1tUo2fTj1IP6FZdVN3Qyf0bTTF4w1q8VqrzxaLrOgoZBjELQKAZAfxnYL55ob9OPU8XyiiIaSU1b6p1Daf+JTMMtwXoW83jI1f2qhVD7TMyD8a1IYTvlWkn0Pn/sYHoepHM7rTQGsZs047ZyqGxHr6f3a95KwtWqwIDAQAB";
        KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
        byte[] decode = Base64.decodeBase64(pubKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decode);
        return factory.generatePublic(spec);
    }

    public static String publicEncrypt(PublicKey publicKey, String data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return encrypt(cipher, data);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密后需要base64解码
     */
    public static String publicDecrypt(PublicKey publicKey, String data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return decrypt(cipher, data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密后需要base64编码
     */
    public static String privateEncrypt(PrivateKey privateKey, String data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return encrypt(cipher, data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String privateDecrypt(PrivateKey privateKey, String data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return decrypt(cipher, data);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String encrypt(Cipher cipher, String data) throws BadPaddingException, IllegalBlockSizeException, IOException {
        byte[] dataBytes = data.getBytes();
        int maxBlock = KeySize / 8 - 11;
        int inputLen = dataBytes.length;
        int offset = 0;
        int line = 0;
        byte[] buffer;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (inputLen > offset) {
            if (inputLen - offset > maxBlock) {
                buffer = cipher.doFinal(dataBytes, offset, maxBlock);
            } else {
                buffer = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            outputStream.write(buffer, 0, buffer.length);
            line++;
            offset = line * maxBlock;
        }
        byte[] bytes = outputStream.toByteArray();
        outputStream.close();
        return Base64.encodeBase64String(bytes);
//        return new String(bytes, "UTF-8");
    }

    private static String decrypt(Cipher cipher, String data) throws BadPaddingException, IllegalBlockSizeException, IOException {
        byte[] dataBytes = Base64.decodeBase64(data);
//        byte[] dataBytes = data.getBytes();
        int maxBlock = KeySize / 8;
        int inputLen = dataBytes.length;
        int offset = 0;
        int line = 0;
        byte[] cache;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (inputLen > offset) {
            if (inputLen - offset > maxBlock)
                cache = cipher.doFinal(dataBytes, offset, maxBlock);
            else
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            outputStream.write(cache, 0, cache.length);
            line++;
            offset = line * maxBlock;
        }
        byte[] bytes = outputStream.toByteArray();
        outputStream.close();
        return new String(bytes, "UTF-8");
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyPair keyPair = generateKeyPair();
        PublicKey publicKey = getPublicKey(keyPair);
        PrivateKey privateKey = getPrivateKey(keyPair);
        String data = "Base64言下之意是一种基于64个可打印字符来表示二进制数据的表示方法!它是一种将二进制编码转换为可打印字符一种。它是MIME编码里面非常常见一种可逆转换二进制方法！";
        String encrypt = privateEncrypt(privateKey, data);
        System.out.println("私钥加密后数据：" + encrypt + ", length--" + encrypt.length());
        String decrypt = publicDecrypt(publicKey, encrypt);
        System.out.println("公钥解密后数据：" + decrypt);
        String pubEncrypt = publicEncrypt(publicKey, data);
        System.out.println("公钥加密后数据：" + pubEncrypt);
        String privateDecrypt = privateDecrypt(privateKey, "SCiIP1yquqfl+8mLmnjj6GDlh/+DT7ytNKf4zYiDmG5Euxq2YDgQgashztJUQjOi52a7n2eF51Yw\n" +
                "lTLFjIDCQ2h6JFwX2QN9mXxb6QEGu4bCwlpxsxQE+jC2t8+ueGMkryD0M0KYvTiQbjE9tlDsfEFh\n" +
                "5q9+SZV9TVCgY3LgkZwOFb/tnXxunrkHw1Fr/T1ZpTYUfXTvduVFXfGDKAKawAtIHEHzQz41SRRl\n" +
                "DS7hydVIB2Z2xl+qbZAxTA4Rc+wPEf2qN3SG/ZGFaMjDaX26w7bLkMasugL/CpJOo6IDiGx5GArM\n" +
                "h9AsUjarjpHJ3Vy/ASXe5uJAABSBWgjsfj3vt0K2oaU7lQkJarV4DtnfAOBhKoOT8yLKmWQXayjw\n" +
                "IqjSdVKVWIHM4vnuZJFoKSG88eENpIH/9GZb5VvlZmjLfJnHMZqwdgjGbEP6EtTDhrL1YKcFld1R\n" +
                "+BPcvGpQor4FlPFwkYF813uCWbI5e8guLkIfBp2BoLG2kjx1DrKn0NtwiKVlwUr4B2XqWA17H1PL\n" +
                "xa4gi8XH/Zf3UK64zNiqVlRLQ6cSavVVSy95fLrvGyYw5onYhsWi+crRgMgu9rt8pTl0v3nZnIG+\n" +
                "LU45BuHKFXmFpD2yLdf7Xy/IMVRmKkRMXGNYJ1CtpCZ8nD5/Quy8pY0q1eQ+7APfWkj/DoblhywF\n" +
                "y2Cn6TVQPdcxw9GYVDxC9JT7lQ5Y1EQLKtrbjK9WCesD3fhP7O9omO8mTFY35EwFpzHMBq4vFLDq\n" +
                "oRZv+hxNDkYMh0mJ/7Me5h4WhqJ4qQPCU+asaDsnesqqfdYya4frY4plGSmd3R+tRyOgMvK7UWCh\n" +
                "otEgcvv7Sc73eaJTB3ARgH5Lj0ku+UC8M5ULIwNTI/mIyxM1wiEH9YA4L++hkcLABGDraXkmc/WX\n" +
                "oYcKrzx7UQnn7zNqCgxVbJTgDIJctoI2PeeDeG5FDvwluSJidaj/Zz7WckjqXVyLYL/AYdPK2hjE\n" +
                "FnbPOYNTiNGYlk3DNmsaj3OQBSeiK9G18eaEMWJFed1GwBofsd3rhVF9Wy01ApJmzp6feYSLM6ff\n" +
                "rkChvpd208FvVck5GMH+3BPeLRvLcjzyI4ymAKl4M1q4ytq/hzT2kUo1Mqgjh8+7W8azT5mR4wez\n" +
                "a9NEADw/TPQDPfHYHQfZjZvoZOz1PVSXPpn7qyNVNBSSZ947tk2x6Vt9BL6PLdvDpFPEYtdb32/b\n" +
                "3xj8llLc8LulIciKcV1D92acSuRX1LDJAnvjuep7f42xYS7w6Bx5Rp/nJ4+HCHo5nVe/F23ZdxIC\n" +
                "0tP3Fhzi9LywCGyAaRTRi6U3xz6zrgnmlZr2YfZ2GJ/piyyoxsBDhp2kcOy9wmE8VkPcp/yNBg==");
        System.out.println("私钥解密后数据：" + privateDecrypt);
    }
}
