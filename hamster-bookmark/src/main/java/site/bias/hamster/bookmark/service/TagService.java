package site.bias.hamster.bookmark.service;

import site.bias.hamster.bookmark.bean.Response;

/**
 * @author chenbinbin
 * @date 2020/2/27 22:31
 */
public interface TagService {

    /**
     * 查询用户得所有标签
     *
     * @return response
     */
    Response query();
}
