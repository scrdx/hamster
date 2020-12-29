package site.bias.hamster.service.impl;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.bias.hamster.bean.Response;
import site.bias.hamster.bean.param.CropParam;
import site.bias.hamster.bean.param.UserParam;
import site.bias.hamster.bean.vo.UserInfoVO;
import site.bias.hamster.config.HamsterConfig;
import site.bias.hamster.constant.Constants;
import site.bias.hamster.constant.ErrorCodeEnum;
import site.bias.hamster.mapper.CategoryRecordMapper;
import site.bias.hamster.mapper.UserRecordMapper;
import site.bias.hamster.pojo.CategoryRecord;
import site.bias.hamster.pojo.UserRecord;
import site.bias.hamster.pojo.UserRecordExample;
import site.bias.hamster.service.UserService;
import site.bias.hamster.util.AvatarUtil;
import site.bias.hamster.util.EncryptUtil;
import site.bias.hamster.util.TokenUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    @Resource
    private CategoryRecordMapper categoryMapper;

    @Resource
    private HamsterConfig config;

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        //初始化该用户的数据
        CategoryRecord rootCategory = new CategoryRecord();
        rootCategory.setTitle("root");
        rootCategory.setUserCode(user.getUserCode());
        rootCategory.setCreated(new Date());
        categoryMapper.insertSelective(rootCategory);

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
        if (user.isRememberMe()) {
            cookie.setMaxAge(Constants.TOKEN_EXPIRE);
        } else {
            cookie.setMaxAge(-1);
        }
        //不使用HTTPS
        cookie.setSecure(false);
        //设置JS脚本不可读
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        servletResponse.addCookie(cookie);
        return Response.build(ErrorCodeEnum.SUCCESS, token);
    }

    @Override
    public Response logout(UserParam user, HttpServletResponse servletResponse) throws Exception {
        Cookie cookie = new Cookie(Constants.TOKEN_KEY, "null");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        servletResponse.addCookie(cookie);
        return Response.build(ErrorCodeEnum.SUCCESS);
    }

    @Override
    public Response config(UserParam userParam) throws Exception {
        UserRecord user = userRecordMapper.selectByPrimaryKey(TokenUtils.getCurrentUserCode());
        user.setModified(new Date());
        String picData = userParam.getAvatarPic();
        CropParam cropParam = userParam.getCropParam();
        String oldAvatar = user.getAvatarUrl();
        if (!StringUtils.isEmpty(picData)) {
            user.setAvatarUrl(AvatarUtil.crop(picData, config.getUploadPath(), cropParam));
            if (!StringUtils.isEmpty(oldAvatar)) {
                Files.deleteIfExists(Paths.get(config.getUploadPath() + oldAvatar));
            }
        }
        user.setNickname(userParam.getNickname());
        userRecordMapper.updateByPrimaryKeySelective(user);
        UserInfoVO userInfo = new UserInfoVO(user);
        userInfo.setAvatarUrl(config.getImgPrefix() + user.getAvatarUrl());
        return Response.build(ErrorCodeEnum.SUCCESS, userInfo);
    }

    @Override
    public Response getUserInfo() throws Exception {
        String userCode = TokenUtils.getCurrentUserCode();
        if (StringUtils.isEmpty(userCode)) {
            return Response.build(ErrorCodeEnum.AUTHENTICATION_ERR);
        }
        UserRecord userRecord = userRecordMapper.selectByPrimaryKey(userCode);
        UserInfoVO user = new UserInfoVO(userRecord);
        if (StringUtils.isEmpty(user.getAvatarUrl())) {
            user.setAvatarUrl("default_avatar.png");
        }
        user.setAvatarUrl(config.getImgPrefix() + user.getAvatarUrl());
        return Response.build(ErrorCodeEnum.SUCCESS, user);
    }
}
