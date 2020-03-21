package site.bias.hamster.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Base64;

/**
 * @author chenbinbin
 * @date 2020/2/28 20:40
 */
@Slf4j
public class Base64Util {

    public static String toBase64(byte[] src) {
        return new String(Base64.getEncoder().encode(src));
    }

    public static String toBase64(File file) {
        String base64 = null;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            base64 = toBase64(data);
        } catch (Exception e) {
            log.error("IO异常", e);
        }
        return base64;
    }

    public static byte[] toBytes(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    public static File toFile(String base64, String fileName) {
        byte[] data = toBytes(base64);
        File file = new File(fileName);
        bytesToFile(data, file);
        return file;
    }

    public static void bytesToFile(byte[] data, File file) {
        if (null == file) {
            throw new IllegalArgumentException("file can not be null.");
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
            fos.flush();
        } catch (Exception e) {
            log.error("IO异常", e);
        }
    }

    public static void main(String[] args) throws Exception {
        File testFile = new File("C:\\Users\\Nari\\Pictures\\nCu9JXNXt0EWjitClqAxaCYoVVLUvXwtHIXCnBn=1k6U81551081633422compressflag.jpg");
        String base64 = toBase64(testFile);
        System.out.println(base64);
    }
}
