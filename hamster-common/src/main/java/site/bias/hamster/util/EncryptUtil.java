package site.bias.hamster.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

/**
 * @author chenbinbin
 * @date 2020/3/22 22:02
 */
public class EncryptUtil {

    public static String sha256(String input) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
        return Hex.encodeHexString(messageDigest.digest(input.getBytes()));
    }

    public static String md5(String input) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        return Hex.encodeHexString(messageDigest.digest(input.getBytes()));
    }
}
