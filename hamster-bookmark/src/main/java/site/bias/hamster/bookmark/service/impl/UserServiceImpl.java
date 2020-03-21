package site.bias.hamster.bookmark.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.bias.hamster.bookmark.bean.Response;
import site.bias.hamster.bookmark.bean.param.UserParam;
import site.bias.hamster.bookmark.service.UserService;

/**
 * @author chenbinbin
 * @date 2020/3/21 22:48
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public Response add(UserParam user) throws Exception {
        return null;
    }

    @Override
    public Response login(UserParam user) throws Exception {
        return null;
    }

    @Override
    public Response logout(UserParam user) throws Exception {
        return null;
    }
}
