package site.bias.hamster.bookmark.service;

import site.bias.hamster.bookmark.bean.Response;

/**
 * @author chenbinbin
 * @date 2020/2/27 22:31
 */
public interface CategoryService {

    /**
     * 添加分类
     *
     * @param title 分类名称
     * @param parentId 父分类ID
     * @return response
     */
    Response add(String title, Integer parentId);

    /**
     * 删除分类
     *
     * @param id 分类ID
     * @return response
     */
    Response delete(Integer id);

    /**
     * 查询分类
     *
     * @return response
     */
    Response query();
}
