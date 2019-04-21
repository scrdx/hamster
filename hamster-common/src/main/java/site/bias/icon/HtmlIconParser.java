package site.bias.icon;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class HtmlIconParser {

    private static final String PATH_PREFIX = "http";

    /**
     * 获取指定网页的ICON URL
     * 匹配<link rel="*" href="" />的元素包含了ICON地址，*有以下几种可能:
     * shortcut icon (早期IE icon的图标声明)
     * icon (Html标准)
     * mask-icon (Safari Pinned Tab icon / Macbook Pro Touch Bar ccon)
     * fluid-icon (Fluid is a way of running web applications as if they were native Mac apps. The fluid icon is the one that displays on the Mac in the dock.)
     *
     * @param url 网页的URL列表
     * @return ICON地址，如果网页没有指定shortcut icon元素/连接URL超时/解析过程发生异常，则返回null
     */
    public static List<String> getIconUrls(String url) {
        List<String> iconUrls = null;
        try {
            Connection connection = Jsoup.connect(url);
            Document doc = connection.get();
            Elements elements = doc.head().getElementsByAttributeValueMatching("rel", ".*icon.*");
            List<String> hrefs = elements.eachAttr("href");
            //将相对路径转化为绝对路径
            iconUrls = hrefs.stream().map(s -> {
                if (!s.startsWith(PATH_PREFIX)) {
                    return getAbsolutePath(s);
                }
                return s;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Get Icon Error", e);
        }
        return iconUrls;
    }

    @SneakyThrows
    private static String getAbsolutePath(String url) {
        URI base = new URI(url);

        URIBuilder builder = new URIBuilder();
        builder.setScheme(base.getScheme())
                .setHost(base.getHost())
                .setPath(url);
        return builder.build().toString();
    }

}
