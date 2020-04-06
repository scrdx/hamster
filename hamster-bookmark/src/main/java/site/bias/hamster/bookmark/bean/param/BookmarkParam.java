package site.bias.hamster.bookmark.bean.param;

import lombok.Data;

/**
 * @author chenbinbin
 * @date 2020/2/27 22:34
 */
@Data
public class BookmarkParam {

    private Integer id;

    private String url;

    private String title;

    private String description;

    private Integer categoryId;

    private String tags;

    private String pic;

    private CropParam cropParam;
}
