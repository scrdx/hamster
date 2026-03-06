package site.bias.hamster.meta;


import lombok.Cleanup;
import lombok.SneakyThrows;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Downloader {


    public static byte[] download(String urlStr) {

        HttpClient client = HttpClients.createDefault();
        try {
            @Cleanup ByteArrayOutputStream bos = new ByteArrayOutputStream();

            HttpGet target = new HttpGet(urlStr);
            target.setHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
            target.setHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
            target.setHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7,ja;q=0.6");
            target.setHeader(HttpHeaders.CACHE_CONTROL, "max-age=0");
            target.setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
            target.setHeader(HttpHeaders.REFERER, "https://www.douban.com/");


            HttpResponse response = client.execute(target);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                response.getEntity().writeTo(bos);
                return bos.toByteArray();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static File download(String url, String fileName) {
        try {
            File file = new File(fileName);
            @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] data = download(url);
            if (null != data) {
                fileOutputStream.write(data);
                return file;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @SneakyThrows
    public static void main(String[] args) {
        List<String> urls = Files.readAllLines(Paths.get("C:\\Users\\nari\\Downloads\\douban\\url.txt"));
        int index = 1;
        for (String url : urls) {
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            download(url, "C:\\Users\\nari\\Downloads\\douban\\" + fileName);
            System.out.println(index++);
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}
