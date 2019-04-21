package site.bias.icon;

import lombok.*;
import site.bias.downloader.Downloader;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Icon {

    private String iconUrl;

    private String name;

    private int width;

    private int height;

    @NonNull
    private byte[] data;

    /**
     * 根据URL获取ICON对象列表
     *
     * @param url page url
     * @return Icon List,里面只有data，其余属性为null
     */
    @Deprecated
    public static List<Icon> getIconsByUrl(String url) {
        List<String> urls = HtmlIconParser.getIconUrls(url);
        List<byte[]> datas = urls.stream()
                .map(Downloader::download)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return datas.stream()
                .map(Icon::new)
                .collect(Collectors.toList());
    }

    /**
     * 根据icon url获取图标数据
     *
     * @param iconUrl 图标url
     * @return icon对象
     */
    public static Icon getIconByIconUrl(String iconUrl) {
        Icon icon = new Icon();
        icon.setData(Downloader.download(iconUrl));
        icon.setIconUrl(iconUrl);
        return icon;
    }
}
