package site.bias.hamster.util;

import net.coobird.thumbnailator.Thumbnails;
import site.bias.hamster.bean.param.CropParam;
import site.bias.hamster.constant.Constants;

import java.io.ByteArrayInputStream;
import java.util.UUID;

/**
 * @author chenbinbin
 * @date 2020/2/28 21:35
 */
public class AvatarUtil {

    public static String crop(String picBase64, String path, CropParam cropParam) throws Exception {
        picBase64 = picBase64.substring(picBase64.indexOf(",") + 1);
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        Thumbnails.of(new ByteArrayInputStream(Base64Utils.toBytes(picBase64)))
                .outputQuality(1.0d)
                .sourceRegion(cropParam.getX(), cropParam.getY(), cropParam.getWidth(), cropParam.getHeight())
                .size(cropParam.getWidth(), cropParam.getHeight())
                .outputFormat(Constants.IMAGE_FORMAT)
                .toFile(path + fileName);
        return fileName + "." + Constants.IMAGE_FORMAT;
    }

    public static void main(String[] args) throws Exception {
        Thumbnails.of("D:\\1582802834.jpg")
                .sourceRegion(500, 500, 500, 500)
                .size(500, 500)
                .outputQuality(1.0d)
                .toFile("D:\\test.jpg");
    }
}
