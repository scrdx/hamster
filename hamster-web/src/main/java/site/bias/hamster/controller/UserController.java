package site.bias.hamster.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.bias.hamster.bean.Response;
import site.bias.hamster.bean.param.UserParam;
import site.bias.hamster.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户注册登录相关接口
 *
 * @author chenbinbin
 * @date 2020/2/27 0:21
 */
@RestController
@Slf4j
@RequestMapping("hamster/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册用户
     *
     * @param userParam 用户信息
     * @return Response
     * @throws Exception 全局异常处理
     */
    @PostMapping("signUp")
    public Response signUp(@RequestBody UserParam userParam) throws Exception {
        return userService.add(userParam);
    }

    /**
     * 登录接口
     *
     * @param userParam 用户信息
     * @return Response
     * @throws Exception 全局异常处理
     */
    @PostMapping("login")
    public Response login(@RequestBody UserParam userParam, HttpServletResponse response) throws Exception {
        return userService.login(userParam, response);
    }

    /**
     * 退出登录
     *
     * @param userParam 用户信息
     * @return Response
     * @throws Exception 全局异常处理
     */
    @PostMapping("logout")
    public Response logout(@RequestBody(required = false) UserParam userParam, HttpServletResponse response) throws Exception {
        return userService.logout(userParam, response);
    }

    /**
     * 设置用户信息
     *
     * @param userParam 用户信息参数
     * @return response
     * @throws Exception 全局异常
     */
    @PostMapping("config")
    public Response config(@RequestBody UserParam userParam) throws Exception {
        return userService.config(userParam);
    }

    /**
     * 查询用户的头像、昵称信息
     *
     * @return response
     * @throws Exception 全局异常
     */
    @GetMapping("getUserInfo")
    public Response getUserInfo() throws Exception {
        return userService.getUserInfo();
    }
}
