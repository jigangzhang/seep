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
        PrivateKey key = keyPair.getPrivate();
        byte[] encoded = key.getEncoded();
        System.out.println("私钥--before base64 -- " + new String(encoded));
        String privateKey = Base64.encodeBase64String(encoded);
        System.out.println("私钥--after base64 -- " + privateKey);
        KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
        byte[] decode = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decode);
        return factory.generatePrivate(keySpec);
    }

    public static PublicKey getPublicKey(KeyPair keyPair) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PublicKey key = keyPair.getPublic();
        byte[] encoded = key.getEncoded();
        System.out.println("公钥--before base64 -- " + new String(encoded));
        String pubKey = Base64.encodeBase64String(encoded);
        System.out.println("公钥--after base64 -- " + pubKey);
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
        String data = "主线程的 Looper 会一直循环调用 MessageQueue 的 next() 来取出队头的 Message 执行，当 Message 执行完后再去取下一个。当 next() 方法在取 Message 时发现队头是一个同步屏障的消息时，就会去遍历整个队列，只寻找设置了异步标志的消息，如果有找到异步消息，那么就取出这个异步消息来执行，否则就让 next() 方法陷入阻塞状态。如果 next() 方法陷入阻塞状态，那么主线程此时就是处于空闲状态的，也就是没在干任何事。所以，如果队头是一个同步屏障的消息的话，那么在它后面的所有同步消息就都被拦截住了，直到这个同步屏障消息被移除，否则主线程就一直不会去处理同步屏障后面的同步消息\n" +
                "\n" +
                "作者：Geekholt\n" +
                "链接：https://www.jianshu.com/p/0a54aa33ba7d\n" +
                "来源：简书\n" +
                "著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。";
        String encrypt = privateEncrypt(privateKey, data);
        System.out.println("私钥加密后数据：" + encrypt + ", length--" + encrypt.length());
        String decrypt = publicDecrypt(publicKey, encrypt);
        System.out.println("公钥解密后数据：" + decrypt);
        String pubEncrypt = publicEncrypt(publicKey, data);
        System.out.println("公钥加密后数据：" + pubEncrypt);
        String privateDecrypt = privateDecrypt(privateKey, pubEncrypt);
        System.out.println("私钥解密后数据：" + privateDecrypt);
    }
}
