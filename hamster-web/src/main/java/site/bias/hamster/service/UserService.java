package site.bias.hamster.service;

import site.bias.hamster.bean.Response;
import site.bias.hamster.bean.param.UserParam;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户相关接口
 *
 * @author chenbinbin
 * @date 2020/2/27 22:31
 */
public interface UserService {

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return response
     * @throws Exception 全局异常处理
     */
    Response add(UserParam user) throws Exception;

    /**
     * 登录
     *
     * @param user     用户信息
     * @param response 用于cookie设置
     * @return response
     * @throws Exception 全局异常处理
     */
    Response login(UserParam user, HttpServletResponse response) throws Exception;

    /**
     * 注销用户
     *
     * @param user     用户信息
     * @param response 用于cookie设置
     * @return response
     * @throws Exception 全局异常处理
     */
    Response logout(UserParam user, HttpServletResponse response) throws Exception;

    /**
     * 配置用户信息
     *
     * @param userParam 用户参数
     * @return response
     * @throws Exception 全局异常处理
     */
    Response config(UserParam userParam) throws Exception;

    /**
     * 获取当前用户的信息
     *
     * @return response
     * @throws Exception 全局异常处理
     */
    Response getUserInfo() throws Exception;
}
