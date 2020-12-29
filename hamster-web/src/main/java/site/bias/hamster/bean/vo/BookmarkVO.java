package site.bias.hamster.bean.vo;

import lombok.Data;
import site.bias.hamster.pojo.BookmarkRecord;

import java.util.Date;
import java.util.List;

/**
 * @author chenbinbin
 * @date 2020/3/22 0:32
 */
@Data
public class BookmarkVO {
    private Integer id;

    private String title;

    private String url;

    private String description;

    private String iconUrl;

    private Integer categoryId;

    private String categoryName;

    private String userCode;

    private Date created;

    private Integer status;

    private Integer isFixed;

    private Long visits;

    private List<TagVO> tagInfoList;

    public BookmarkVO() {
    }

    public BookmarkVO(BookmarkRecord bookmarkRecord) {
        this.id = bookmarkRecord.getId();
        this.title = bookmarkRecord.getTitle();
        this.url = bookmarkRecord.getUrl();
        this.description = bookmarkRecord.getDescription();
        this.iconUrl = bookmarkRecord.getIconUrl();
        this.categoryId = bookmarkRecord.getCategoryId();
        this.userCode = bookmarkRecord.getUserCode();
        this.created = bookmarkRecord.getCreated();
        this.status = bookmarkRecord.getStatus();
        this.isFixed = bookmarkRecord.getIsFixed();
        this.visits = bookmarkRecord.getVisits();
    }
}
