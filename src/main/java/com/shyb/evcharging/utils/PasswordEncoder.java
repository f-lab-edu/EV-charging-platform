package com.shyb.evcharging.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 패스워드 암호화 작업을 처리합니다.
 */
public class PasswordEncoder {

    public static String encrypt(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());
            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

}
