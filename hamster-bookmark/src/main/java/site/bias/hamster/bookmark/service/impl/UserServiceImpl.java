package site.bias.hamster.bookmark.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.bias.hamster.bookmark.bean.Response;
import site.bias.hamster.bookmark.bean.param.UserParam;
import site.bias.hamster.bookmark.constant.Constants;
import site.bias.hamster.bookmark.constant.ErrorCodeEnum;
import site.bias.hamster.bookmark.mapper.UserRecordMapper;
import site.bias.hamster.bookmark.pojo.UserRecord;
import site.bias.hamster.bookmark.pojo.UserRecordExample;
import site.bias.hamster.bookmark.service.UserService;
import site.bias.hamster.bookmark.util.EncryptUtil;
import site.bias.hamster.bookmark.util.TokenUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author chenbinbin
 * @date 2020/3/21 22:48
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserRecordMapper userRecordMapper;

    /**
     * Cookie的超时时间为30天
     */
    private static final Integer MAX_AGE = 30 * 24 * 3600;

    @Override
    public Response add(UserParam user) throws Exception {
        UserRecordExample example = new UserRecordExample();
        UserRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserCodeEqualTo(user.getUserCode());
        List<UserRecord> userRecords = userRecordMapper.selectByExample(example);
        if (userRecords.size() > 0) {
            return new Response(ErrorCodeEnum.BUSINESS_ERR.getCode(), "该用户编码已被注册!");
        }
        UserRecord userRecord = new UserRecord();
        userRecord.setUserCode(user.getUserCode());
        userRecord.setNickname(user.getNickname());
        userRecord.setPassword(EncryptUtil.md5(user.getPassword()));
        userRecord.setCreated(new Date());
        userRecordMapper.insert(userRecord);

        return Response.build(ErrorCodeEnum.SUCCESS, user.getUserCode());
    }

    @Override
    public Response login(UserParam user, HttpServletResponse servletResponse) throws Exception {
        UserRecordExample example = new UserRecordExample();
        UserRecordExample.Criteria criteria = example.createCriteria();
        criteria.andUserCodeEqualTo(user.getUserCode());

        List<UserRecord> userRecords = userRecordMapper.selectByExample(example);
        if (userRecords.size() == 0) {
            return new Response(ErrorCodeEnum.AUTHENTICATION_ERR.getCode(), "用户名或密码错误");
        }
        UserRecord userRecord = userRecords.get(0);
        String password = EncryptUtil.md5(user.getPassword());
        if (!password.equals(userRecord.getPassword())) {
            return new Response(ErrorCodeEnum.AUTHENTICATION_ERR.getCode(), "用户名或密码错误");
        }
        //生成TOKEN
        String token = TokenUtils.generateToken(user.getUserCode(), user.getNickname());
        Cookie cookie = new Cookie(Constants.TOKEN_KEY, token);
        cookie.setMaxAge(MAX_AGE);
        cookie.setSecure(false);
        //TODO 调试使用
        cookie.setDomain("127.0.0.1");
        cookie.setPath("/");
        servletResponse.addCookie(cookie);
        return Response.build(ErrorCodeEnum.SUCCESS, token);
    }

    @Override
    public Response logout(UserParam user, HttpServletResponse servletResponse) throws Exception {
        Cookie cookie = new Cookie(Constants.TOKEN_KEY, "");
        cookie.setMaxAge(0);
        servletResponse.addCookie(cookie);
        return Response.build(ErrorCodeEnum.SUCCESS);
    }
}
