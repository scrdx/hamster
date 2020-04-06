package site.bias.hamster.bookmark.util;


import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import site.bias.hamster.bookmark.constant.Constants;
import site.bias.hamster.util.Base64Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenbinbin
 * @date 2020/3/22 22:10
 */
public class TokenUtils {

    private static final String SECRET = "I don't want go to work.";

    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    /**
     * 生成token,暂时不设置失效时间
     *
     * @param userCode 用户编码
     * @param nickname 昵称
     * @return token
     */
    public static String generateToken(String userCode, String nickname) {
        Map<String, Object> header = new HashMap<>(2);
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        return JWT.create()
                .withHeader(header)
                .withClaim("userCode", userCode)
                .withClaim("nickname", nickname)
                .withClaim("created", new Date())
                .sign(ALGORITHM);
    }

    public static String decode(String token) {
        return new String(Base64Utils.toBytes(JWT.decode(token).getPayload()));
    }

    public static String getUserCode(String token) {
        if (null == token) {
            return null;
        }
        Claim userCode = JWT.decode(token).getClaim("userCode");
        if (null == userCode) {
            return null;
        }
        return userCode.asString();
    }

    public static void verify(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        verifier.verify(token);
    }

    /**
     * 获取当前请求的用户编码,调用这个方法意味着token校验已经成功
     *
     * @return 用户编码
     */
    public static String getCurrentUserCode() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (null == cookies) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (Constants.TOKEN_KEY.equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        return getUserCode(token);
    }

    public static void main(String[] args) {
        String token = generateToken("inory", "inory");
        System.out.println(token);
        String decoded = decode(token);
        System.out.println(decoded);
        System.out.println(getUserCode(token));
    }

}
