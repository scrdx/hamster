package site.bias.hamster.constant;

/**
 * 错误码1xx为请求端导致的错误
 * 错误码2xx为服务端错误，包括业务逻辑异常/服务器异常/数据库操作异常
 *
 * @author chenbinbin
 * @date 2020/2/27 22:22
 */
public enum ErrorCodeEnum {
    /**
     * 成功
     */
    SUCCESS(0, "success"),

    /**
     * 鉴权失败
     */
    AUTHENTICATION_ERR(100, "鉴权失败"),

    /**
     * 请求参数异常
     */
    BAD_PARAM(101, "参数异常"),

    /**
     * 接口调用过于频繁
     */
    EXCEED_THRESHOLD(102, "调用过于频繁,超过阈值"),

    /**
     * 业务逻辑异常
     */
    BUSINESS_ERR(201, "业务异常"),

    /**
     * 服务器异常
     */
    SERVER_ERR(202, "服务器异常"),

    /**
     * 数据库操作异常
     */
    DB_ERR(203, "数据库操作异常");

    private int code;
    private String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
