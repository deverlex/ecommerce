package vn.needy.ecommerce.common.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Utils {

    public static class ImageUtils {

        public static InputStream base64ToInputStream(String encodedImage) {
            String imgDataBytes = encodedImage.substring(encodedImage.indexOf(",") + 1);
            return new ByteArrayInputStream(Base64.getMimeDecoder().decode(imgDataBytes.getBytes(StandardCharsets.UTF_8)));
        }
    }
}
