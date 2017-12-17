package com.marsthink.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.marsthink.app.R;

/**
 * Created by zhoumao on 2017/11/11.
 * Description:
 */

public class JniActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        TextView textView = findViewById(R.id.tv_jni);
//        String entrypt = EncryptUtil.aes_encrypt("hi jamaljo");
//        textView.setText("加密： " + entrypt + " \n 解密： " + EncryptUtil.aes_decrypt(entrypt));
    }
}
