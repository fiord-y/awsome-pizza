package com.fiordy.pizza.order.util;

import java.util.Random;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TokenUtil {

    // Generate a random token
    private static final int TOKEN_LENGTH = 6;
    public static String generateToken() {
        StringBuilder token = new StringBuilder();
        Random random = new Random();

        // Define alphabet
        String alphabet = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Generate 8 random letters
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));
            token.append(randomChar);
        }
        return token.toString();
    }

    // Hash the token using SHA-256
    public static String hashToken(String token) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(token.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Verify if the presented token matches the stored one
    public static boolean verifyToken(String presentedToken, String storedHash) throws NoSuchAlgorithmException {
        String presentedHash = hashToken(presentedToken);
        return presentedHash.equals(storedHash);
    }
}
