package site.bias.hamster.bookmark.bean.param;

import lombok.Data;

/**
 * @author chenbinbin
 * @date 2020/4/6 16:25
 */
@Data
public class CropParam {
    /**
     * px
     */
    private Integer x;

    private Integer y;

    private Integer width;

    private Integer height;

    /**
     *  deg
     */
    private Integer rotate;

    private Integer scaleX;

    private Integer scaleY;
}
