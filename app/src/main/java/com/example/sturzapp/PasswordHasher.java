/*package com.example.sturzapp;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
    public static String hashPassword(String passwordRP) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(passwordRP.getBytes(StandardCharsets.UTF_8));
            return bytesToHexString(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            String hexString = Integer.toHexString(0xff & b);
            if (hexString.length() == 1) {
                result.append('0');
            }
            result.append(hexString);
        }
        return result.toString();
    }
}*/
