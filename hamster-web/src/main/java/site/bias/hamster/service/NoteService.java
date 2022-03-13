package site.bias.hamster.service;

import site.bias.hamster.bean.Response;
import site.bias.hamster.pojo.NoteCategoryRecord;
import site.bias.hamster.pojo.NoteRecord;

import java.util.Date;

/**
 * 便签相关接口（包含todo）
 * @author chenbinbin
 * @date 2021-01-03 19:00
 */
public interface NoteService {

    /**
     * 新增条目
     * @param note 便签实体
     * @return -
     */
    Response add(NoteRecord note);

    /**
     * 删除便签
     * @param id id
     * @return -
     */
    Response delete(Integer id);

    /**
     * 物理删除
     * @param id id
     * @return -
     */
    Response physicalDelete(Integer id);

    /**
     * 更新
     * @param note -
     * @return -
     */
    Response update(NoteRecord note);

    /**
     * 查询
     *
     * @param key 关键字
     * @param recursive 是否返回子分类下的便签
     * @param type 条目类型
     * @param categoryId 分类ID
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return -
     */
    Response query(String key, Boolean recursive, Integer type, Integer categoryId, Date begin, Date end, Integer pageNum, Integer pageSize);

    /**
     * 完成
     * @param id -
     * @return -
     */
    Response finish(Integer id);

    /**
     * 添加便签分类
     *
     * @param noteCategory -
     * @return -
     */
    Response addCategory(NoteCategoryRecord noteCategory);

    /**
     * 删除便签分类（标记删除）
     * @param id -
     * @return -
     */
    Response deleteCategory(Integer id);

    /**
     * 更新分类
     *
     * @param noteCategory -
     * @return -
     */
    Response updateCategory(NoteCategoryRecord noteCategory);


}
