package site.bias.hamster.bookmark.dao;

import site.bias.hamster.bookmark.bean.vo.TagVO;

import java.util.List;

/**
 * @author chenbinbin
 * @date 2020/3/21 23:47
 */
public interface TagDAO {

    /**
     * 获取tag id以 {@link site.bias.hamster.bookmark.constant.Constants#SPLIT_CHARACTER}分隔的字符串
     * 如果该用户已经建立过该tag,则使用已存在的tagId,否则新建tag
     *
     * @param tagNames tag名称的列表,以@link site.bias.hamster.bookmark.constant.Constants#SPLIT_CHARACTER 分隔
     * @param userCode 用户编码
     * @return 以 {@link site.bias.hamster.bookmark.constant.Constants#SPLIT_CHARACTER}分隔的字符串
     */
    String getTagIds(String tagNames, String userCode);

    /**
     * 获取Tag信息列表
     *
     * @param tagIds   tagId列表
     * @param userCode 用户编码
     * @return TagVO列表
     */
    List<TagVO> getTagInfos(String tagIds, String userCode);
}
