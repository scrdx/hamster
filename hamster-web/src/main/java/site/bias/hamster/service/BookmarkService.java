package site.bias.hamster.service;

import site.bias.hamster.bean.param.BookmarkParam;
import site.bias.hamster.bean.Response;

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

    /**
     * 获取随机书签列表
     * @param size 数量
     * @return 随机书签列表
     * @throws Exception -
     */
    Response getRandom(Integer size) throws Exception;

    /**
     * 获取常用书签列表
     * 包含两部分: 固定部分 访问次数多的部分
     * @param size 数量
     * @return 常用书签列表
     * @throws Exception -
     */
    Response getOften(Integer size) throws Exception;

    /**
     * 将书签固定
     * @param id 书签ID
     * @return 固定结果
     * @throws Exception -
     */
    Response fix(Integer id) throws Exception;

    /**
     * 取消固定
     * @param id 书签ID
     * @return -
     * @throws Exception -
     */
    Response unfix(Integer id) throws Exception;

    /**
     * 访问次数+1
     * @param id 书签ID
     * @return -
     * @throws Exception -
     */
    Response increase(Integer id) throws Exception;
}
