package site.bias.hamster.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenbinbin
 * @date 2020/2/29 16:19
 */
@ConfigurationProperties(prefix = "hamster")
@Configuration
@Setter
public class HamsterConfig {
    private String uploadPath;

    private String imgPrefix;

    public String getUploadPath(){
        return uploadPath.endsWith("/")||uploadPath.endsWith("\\") ? uploadPath :uploadPath + "/";
    }

    public String getImgPrefix() {
        return imgPrefix;
    }
}
