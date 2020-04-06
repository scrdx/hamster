package site.bias.hamster.bookmark.bean.vo;

import lombok.Data;
import site.bias.hamster.bookmark.pojo.UserRecord;

/**
 * @author chenbinbin
 * @date 2020/4/6 17:30
 */
@Data
public class UserInfoVO {

    private String userCode;

    private String nickname;

    private String avatarUrl;

    public UserInfoVO() {
    }

    public UserInfoVO(UserRecord userRecord) {
        this.userCode = userRecord.getUserCode();
        this.nickname = userRecord.getNickname();
        this.avatarUrl = userRecord.getAvatarUrl();
    }
}
