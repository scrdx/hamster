package site.bias.hamster.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import site.bias.hamster.bean.Response;
import site.bias.hamster.constant.Constants;
import site.bias.hamster.constant.ErrorCodeEnum;
import site.bias.hamster.util.TokenUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenbinbin
 * @date 2020/3/22 21:54
 */
@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if (path.contains("hamster/user") || path.contains("swagger") || path.contains("static")) {
            return true;
        }
        response.setContentType("application/json;charset=utf-8");
        Cookie[] cookies = request.getCookies();
        if (null == cookies || cookies.length < 1) {
            Response rep = Response.build(ErrorCodeEnum.AUTHENTICATION_ERR);
            response.getOutputStream().write(JSONObject.toJSONString(rep).getBytes());
            log.warn("Token鉴权失败:Cookie为空");
            return false;
        }
        String token = null;
        for (Cookie cookie : cookies) {
            if (Constants.TOKEN_KEY.equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        if (StringUtils.isEmpty(token)) {
            Response rep = Response.build(ErrorCodeEnum.AUTHENTICATION_ERR);
            response.getOutputStream().write(JSONObject.toJSONString(rep).getBytes());
            log.warn("Token鉴权失败:Token为空");
            return false;
        }
        try {
            TokenUtils.verify(token);
        } catch (JWTVerificationException exception) {
            Response rep = Response.build(ErrorCodeEnum.AUTHENTICATION_ERR);
            response.getOutputStream().write(JSONObject.toJSONString(rep).getBytes());
            log.warn("Token校验失败:无效Token:{}", token);
            return false;
        }

        return true;
    }
}
