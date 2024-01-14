package com.byteopus.redkino.utils;

import jakarta.annotation.Nullable;
import java.util.Base64;

public class ImageUtil {

    public String getImgString(@Nullable byte[] img) {
        if (img != null) {
            return Base64.getEncoder().encodeToString(img);
        } else {
            return null; // or an empty string, depending on your requirements
        }
    }
}
