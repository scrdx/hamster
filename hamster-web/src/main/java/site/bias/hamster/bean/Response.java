package site.bias.hamster.bean;

import lombok.Data;
import site.bias.hamster.constant.ErrorCodeEnum;

/**
 * @author chenbinbin
 * @date 2020/2/27 22:05
 */
@Data
public class Response {

    private int code;

    private String message;

    private Object data;

    private Integer pageNum;

    private Integer pageSize;

    private Integer pages;

    private Long totalCount;

    public Response() {
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(int code, String message, Object data) {
        this(code, message);
        this.data = data;
    }

    public Response(int code, String message, Object data, Integer pageNum, Integer pageSize, Integer pages, Long totalCount) {
        this(code, message);
        this.data = data;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = pages;
        this.totalCount = totalCount;
    }

    public static Response build(ErrorCodeEnum errorCode) {
        return new Response(errorCode.getCode(), errorCode.getMessage());
    }

    public static Response build(ErrorCodeEnum errorCode, Object data) {
        return new Response(errorCode.getCode(), errorCode.getMessage(), data);
    }

    public static Response build(ErrorCodeEnum errorCode, Object data, Integer pageNum, Integer pageSize, Integer pages, Long totalCount) {
        return new Response(errorCode.getCode(), errorCode.getMessage(), data, pageNum, pageSize, pages, totalCount);
    }


}
