package site.bias.hamster.bean.param;

import lombok.Data;

/**
 * @author chenbinbin
 * @date 2020/2/27 23:00
 */
@Data
public class UserParam {

    private String userCode;

    private String nickname;

    private String password;

    private String avatarPic;

    private boolean rememberMe;

    private CropParam cropParam;
}
