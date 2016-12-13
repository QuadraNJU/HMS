package nju.quadra.hms.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * Created by adn55 on 2016/11/25.
 */
public class PassHash {

    public static String hash(String str) {
        return sha256(str);
    }

    public static String sha256(String original) {
        // init digest
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            // e.printStackTrace();
            return "";
        }
        // get bytes from original string
        byte[] bytes;
        try {
            bytes = digest.digest(original.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // e.printStackTrace();
            return "";
        }
        // convert bytes to hex string
        String result = "";
        for (byte b : bytes) {
            result = result + Integer.toString((b & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

}
