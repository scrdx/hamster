package site.bias.hamster.bookmark.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.bias.hamster.bookmark.bean.Response;
import site.bias.hamster.bookmark.bean.param.UserParam;
import site.bias.hamster.bookmark.service.UserService;

import javax.annotation.Resource;

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
    public Response login(@RequestBody UserParam userParam) throws Exception {
        return userService.login(userParam);
    }

    /**
     * 退出登录
     *
     * @param userParam 用户信息
     * @return Response
     * @throws Exception 全局异常处理
     */
    @PostMapping("logout")
    public Response logout(@RequestBody UserParam userParam) throws Exception {
        return userService.logout(userParam);
    }
}
