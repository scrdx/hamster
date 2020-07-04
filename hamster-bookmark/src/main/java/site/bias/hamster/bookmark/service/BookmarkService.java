package site.bias.hamster.bookmark.service;

import site.bias.hamster.bookmark.bean.param.BookmarkParam;
import site.bias.hamster.bookmark.bean.Response;

/**
 * @author chenbinbin
 * @date 2020/2/27 22:31
 */
public interface BookmarkService {

    /**
     * 添加书签
     *
     * @param bookmark 书签实体
     * @return response
     * @throws Exception 全局异常
     */
    Response add(BookmarkParam bookmark) throws Exception;

    /**
     * 删除书签
     *
     * @param id 书签ID
     * @return response
     * @throws Exception 全局异常
     */
    Response delete(Integer id) throws Exception;

    /**
     * 修改书签
     *
     * @param bookmark 书签实体
     * @return response
     * @throws Exception 全局异常
     */
    Response update(BookmarkParam bookmark) throws Exception;

    /**
     * 查询书签数据
     *
     * @param key 标题或者标签
     * @param categoryId 分类ID
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return response
     * @throws Exception 全局异常
     */
    Response query(String key, Integer categoryId, Integer pageNum, Integer pageSize) throws Exception;

    /**
     * 根据ID查询
     *
     * @param id 书签ID
     * @return response
     * @throws Exception 全局异常
     */
    Response queryById(Integer id) throws Exception;

    /**
     * 根据标签ID查询
     *
     * @param tagId 标签ID
     * @return response
     * @throws Exception 全局异常
     */
    Response queryByTagId(Integer tagId) throws Exception;

    /**
     * 根据URL获取对应网站的标题、描述等元信息
     *
     * @param url url
     * @return 元信息
     * @throws Exception 全局异常
     */
    Response getMetaInfoByUrl(String url) throws Exception;
}
