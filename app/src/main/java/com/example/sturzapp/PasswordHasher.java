package com.example.sturzapp;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Die Klasse {@code PasswordHasher} bietet Methoden zum Hashen von Passwörtern an
 * Sie verwendet den SHA-256-Algorithmus zum Hashen der Passwörter
 */
public class PasswordHasher {

    /**
     * Diese Methode hash das übergebene Passwort mit Hilfe des SHA-256-Algorithmus
     * @param passwordRP ist das Passwort das gehasht wird
     * @return  das gehashte Passworts als Hexadezimalzeichenkette
     */
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

    /**
     * Diese Methode konvertiert ein Byte-Array in eine Hexadezimalzeichenkette
     * @param bytes das zu konvertierende Byte-Array
     * @return  die Hexadezimalzeichenkette, die das Byte-Array repräsentiert
     */
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
}
