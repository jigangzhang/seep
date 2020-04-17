package com.god.seep.push;

import com.god.seep.util.EncryptUtil;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class PushTest implements IPush {
    private List<IPush> channels;

    public PushTest() {
        channels = new ArrayList<>();
//        channels.add(new XingePush());
        channels.add(new GetuiPush());
        channels.add(new ALiPush());
        channels.add(new JGPush());
    }

    @Override
    public void pushAllDevice() {
        for (IPush channel : channels) {
            channel.pushAllDevice();
        }
    }

    @Override
    public void queryPushStatus() {
        for (IPush channel : channels) {
            channel.queryPushStatus();
        }
    }

    @Override
    public void queryDeviceCount() {
        for (IPush channel : channels) {
            channel.queryDeviceCount();
        }
    }

    public static class Person {
        int age;
        String name;

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String args[]) {
        float f = 103.400f;
        BigDecimal maxDecimal = new BigDecimal(f);
        String max = maxDecimal.stripTrailingZeros().toPlainString();
        System.out.println("no zero-f-" + max);
//        if (true)
//            return;
        Person person = new Person();
        person.age = 1;
        person.name = "pig";
        final Person piter = person;
        piter.age = 45;
        System.out.println(person.toString());
        System.out.println(piter.toString());
//        if (true)
//            return;
        PushTest test = new PushTest();
        int count = 1;
        while (true) {
            test.pushAllDevice();
            count--;
            if (count == 0)
                break;
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        test.queryDeviceCount();
        test.queryPushStatus();
//        System.out.println("md5-->" + formatMd5("123456a"));//9cbf8a4dcb8e30682b927f352d6559a0
//        System.out.println("密码验证-->" + isPwdLegal("a4"));
//        int i = 50;
//        i = i++ * 2;
////        int j=i++;
//        System.out.println("求余：" + 11 % 16);
//        System.out.println((char)16);
//        String data = "abcdefghigklmn";
//        System.out.println("encrypt data : " + data);
//        String encrypted = EncryptUtil.encode(EncryptUtil.key, data);
//        System.out.println("encrypted data : " + encrypted);
//        System.out.println("decrypted data : " +
//                EncryptUtil.decode(EncryptUtil.key, "QyDGZQ67sFX+5KyrvJwo0Q=="));
    }

    public static boolean isPwdLegal(String pwd) {
        String regEx = "^(?![0-9]+$)(?![a-zA-Z]+$)(?!([^(0-9a-zA-Z)])+$).{2,}$";
        return pwd.matches(regEx);
    }

    public static String formatMd5(String pwd) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(pwd.getBytes());
            byte[] bytes = digest.digest();
            return toHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            String hexString = Integer.toHexString(b & 0xff);
            if (hexString.length() == 1)
                hexString = "0" + hexString;
            builder.append(hexString);
        }
        return builder.toString();
    }
}
