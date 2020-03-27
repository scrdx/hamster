package site.bias.hamster.bookmark.util;

import net.coobird.thumbnailator.Thumbnails;

/**
 * @author chenbinbin
 * @date 2020/2/28 21:35
 */
public class AvatarUtil {

    public void crop() {

    }

    public static void main(String[] args) throws Exception{
        Thumbnails.of("D:\\1582802834.jpg")
                .sourceRegion(500,500,500,500)
                .size(500,500)
                .outputQuality(1.0d)
                .toFile("D:\\test.jpg");
    }
}
