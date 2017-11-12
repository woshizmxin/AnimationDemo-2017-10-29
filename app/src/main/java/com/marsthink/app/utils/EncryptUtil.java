package com.marsthink.app.utils;

/**
 * Created by zhoumao on 2017/11/11.
 * Description:
 */

public class EncryptUtil {
    public static native String aes_encrypt(String code);

    public static native String aes_decrypt(String code);

    static {
        System.loadLibrary("jniencrypt");
    }
}
