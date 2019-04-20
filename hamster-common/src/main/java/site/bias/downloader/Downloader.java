package site.bias.downloader;


import com.sun.istack.internal.NotNull;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

public class Downloader {


    public static byte[] download(@NotNull String urlStr) {

        HttpClient client = HttpClients.createDefault();
        try {
            @Cleanup ByteArrayOutputStream bos = new ByteArrayOutputStream();

            HttpGet target = new HttpGet(urlStr);
            target.setHeader(HttpHeaders.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            target.setHeader(HttpHeaders.CONNECTION, "close");

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
        download("https://cdn.aixifan.com/ico/favicon.ico", "D:\\acfun.ico");


    }
}
