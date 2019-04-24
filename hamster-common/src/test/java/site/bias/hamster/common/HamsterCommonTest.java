package site.bias.hamster.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import site.bias.parser.HtmlParser;
import site.bias.icon.Icon;

import java.util.List;

public class HamsterCommonTest {

    @Test
    void htmlIconParserTest() {
        List<String> str = HtmlParser.getIconUrls("http://www.acfun.cn/v/ac10130683");
        Assertions.assertNotNull(str);
    }

    @Test
    void IconTest() {
        Icon icon = Icon.getIconByIconUrl("https://cdn.aixifan.com/ico/favicon.ico");
        Assertions.assertNotNull(icon);
    }
}
