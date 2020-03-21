package site.bias.hamster.bookmark.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import site.bias.hamster.bookmark.bean.Response;
import site.bias.hamster.bookmark.constant.ErrorCodeEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenbinbin
 * @date 2020/2/27 19:32
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Response exception(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        log.error("发生未知异常", exception);
        return Response.build(ErrorCodeEnum.FAIL);
    }

}
