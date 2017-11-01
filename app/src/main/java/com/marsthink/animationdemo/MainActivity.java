package com.marsthink.animationdemo;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.marsthink.animationdemo.animations.TVCloseAnimation;
import com.marsthink.animationdemo.animations.ThreeDAnimation;
import com.marsthink.animationdemo.animations.VibrateAnimation;
import com.marsthink.animationdemo.views.XferModeProgressView;

public class MainActivity extends AppCompatActivity {

    ImageView mImageView;
    Button mButton;
    XferModeProgressView mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        animation();
    }

    void initViews() {
        mProgressView = (XferModeProgressView)findViewById(R.id.view_progress);
        mImageView = (ImageView) findViewById(R.id.img);
        mButton = (Button) findViewById(R.id.btn_start);
        mImageView.getHeight();
        mImageView.getMeasuredHeight();
        mImageView.setImageTintList(ColorStateList.valueOf(Color.WHITE));
        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, XBallsFallActivity.class));
                mProgressView.startAnimation();
            }
        });
    }

    void animation() {
        // 创建抖一抖动画对象
        final VibrateAnimation tremble = new VibrateAnimation();
        tremble.setDuration(800);// 持续时间800ms，持续时间越短频率越高
        tremble.setRepeatCount(2);// 重复次数，不包含第一次

        final TVCloseAnimation tvCloseAnimation = new TVCloseAnimation();

        final ThreeDAnimation threeDAnimation = new ThreeDAnimation();
        threeDAnimation.setRotateY(30);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView.startAnimation(threeDAnimation);
            }
        });
    }
}
