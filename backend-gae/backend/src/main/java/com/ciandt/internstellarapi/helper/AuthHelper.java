package com.ciandt.internstellarapi.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by falcao on 06/10/16.
 */

public class AuthHelper {

    private static final String ALGORITIMO_HASH_SENHA = "MD5";

    public static String generateHashFromText(String text) throws NoSuchAlgorithmException {
        // Create MessageDigest instance for MD5
        MessageDigest md = MessageDigest.getInstance(ALGORITIMO_HASH_SENHA);
        //Add password bytes to digest
        md.update(text.getBytes());
        //Get the hash's bytes
        byte[] bytes = md.digest();
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
