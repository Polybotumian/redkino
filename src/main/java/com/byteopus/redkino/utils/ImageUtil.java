package com.byteopus.redkino.utils;

import java.util.Base64;

public class ImageUtil {
    public String GetImgString(byte[] img)
    {
        return Base64.getEncoder().encodeToString(img);
    }
}
