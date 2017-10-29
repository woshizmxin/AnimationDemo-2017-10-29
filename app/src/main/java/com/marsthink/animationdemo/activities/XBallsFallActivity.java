package com.marsthink.animationdemo.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.marsthink.animationdemo.R;
import com.marsthink.animationdemo.views.XBallView;

/**
 * 小球下落动画加强版XBallsFallActivity，增加了小球桌底时的压扁、回弹动画
 * Created by wondertwo on 2016/3/20.
 */
public class XBallsFallActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_ball_fall);
        LinearLayout xContainer = (LinearLayout) findViewById(R.id.xcontainer);
        // 设置要显示的view组件
        xContainer.addView(new XBallView(this));
    }
}
