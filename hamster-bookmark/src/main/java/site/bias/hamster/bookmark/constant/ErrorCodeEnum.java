package site.bias.hamster.bookmark.constant;

/**
 * @author chenbinbin
 * @date 2020/2/27 22:22
 */
public enum ErrorCodeEnum {
    /**
     * 成功
     */
    SUCCESS(0,"success"),

    /**
     * 失败
     */
    FAIL(1,"fail");

    private int code;
    private String message;

    ErrorCodeEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
