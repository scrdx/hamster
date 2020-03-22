package site.bias.hamster.bookmark.bean.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author chenbinbin
 * @date 2020/3/22 18:42
 */
@Data
public class CategoryVO {
    private Integer id;

    private String title;

    private String description;

    private Integer parentId;

    private Date created;

    private List<CategoryVO> children;
}
