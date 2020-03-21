package site.bias.hamster.bookmark.config;

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
    private String path;

    public String getPath(){
        return path.endsWith("/")||path.endsWith("\\") ? path :path + "/";
    }
}
