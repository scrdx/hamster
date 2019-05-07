package site.bias.parser;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import site.bias.bean.WebApplication;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 通过Html内容解析网站的图标、标题、描述等信息
 */

@Slf4j
public class HtmlParser {

    private static final String PATH_PREFIX = "http";

    public static List<String> getIconUrls(String url) {
        return getIconUrls(getDocumentByUrl(url), url);
    }

    public static WebApplication getWebAppByUrl(String url) {
        WebApplication app = new WebApplication();


        return app;
    }


    /**
     * 获取指定网页的ICON URL
     * 匹配<link rel="*" href="" />的元素包含了ICON地址，*有以下几种可能:
     * shortcut icon (早期IE icon的图标声明)
     * icon (Html标准)
     * mask-icon (Safari Pinned Tab icon / Macbook Pro Touch Bar ccon)
     * fluid-icon (Fluid is a way of running web applications as if they were native Mac apps. The fluid icon is the one that displays on the Mac in the dock.)
     *
     * @param doc Document对象
     * @return ICON地址，如果网页没有指定shortcut icon元素/连接URL超时/解析过程发生异常，则返回null
     */
    private static List<String> getIconUrls(Document doc, String url) {
        List<String> iconUrls;
        Elements elements = doc.head().getElementsByAttributeValueMatching("rel", ".*icon.*");
        List<String> hrefs = elements.eachAttr("href");
        //将相对路径转化为绝对路径
        iconUrls = hrefs.stream().map(s -> {
            if (!s.startsWith(PATH_PREFIX)) {
                return getAbsolutePath(url, s);
            }
            return s;
        }).collect(Collectors.toList());
        return iconUrls;
    }

    private static Document getDocumentByUrl(String url) {
        Document doc = null;
        try {
            Connection connection = Jsoup.connect(url);
            doc = connection.get();
        } catch (IOException e) {
            log.error("Get Html Content Error", e);
        }
        return doc;
    }

    @SneakyThrows
    private static String getAbsolutePath(String url, String path) {
//        URI base = new URI(url);
//
//        URIBuilder builder = new URIBuilder();
//        builder.setScheme(base.getScheme())
//                .setHost(base.getHost())
//                .setPath(path);
//        return builder.build().toString();
        return "";
    }

    public static void main(String[] args) {
        getIconUrls("https://huaban.com/");
    }


}
