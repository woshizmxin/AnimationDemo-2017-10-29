package com.marsthink.app.activities;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import com.marsthink.app.R;

/**
 * ObjectAnimator高级实例探究
 * Created by wondertwo on 2016/3/22.
 */
public class CircleProgressActivity extends Activity {

    private LinearLayout mPoint_1;
    private LinearLayout mPoint_2;
    private LinearLayout mPoint_3;
    private LinearLayout mPoint_4;
    String TAG = "animation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_ball);

        mPoint_1 = (LinearLayout) findViewById(R.id.ll_point_circle_1);
        mPoint_2 = (LinearLayout) findViewById(R.id.ll_point_circle_2);
        mPoint_3 = (LinearLayout) findViewById(R.id.ll_point_circle_3);
        mPoint_4 = (LinearLayout) findViewById(R.id.ll_point_circle_4);

        Button startAni = (Button) findViewById(R.id.start_ani_2);
        startAni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginPropertyAni();
            }
        });
    }

    /**
     * 开启动画
     */
    private void beginPropertyAni() {
        ObjectAnimator animator_1 = ObjectAnimator.ofFloat(
                mPoint_1,
                "rotation",
                0,
                360);
        animator_1.setDuration(2000);
        animator_1.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator animator_2 = ObjectAnimator.ofFloat(
                mPoint_2,
                "rotation",
                0,
                360);
        animator_2.setStartDelay(150);
        animator_2.setDuration(2000 + 150);
        animator_2.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator animator_3 = ObjectAnimator.ofFloat(
                mPoint_3,
                "rotation",
                0,
                360);
        animator_3.setStartDelay(2 * 150);
        animator_3.setDuration(2000 + 2 * 150);
        animator_3.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator animator_4 = ObjectAnimator.ofFloat(
                mPoint_4,
                "rotation",
                0,
                360);
        animator_4.setStartDelay(3 * 150);
        animator_4.setDuration(2000 + 3 * 150);
        animator_4.setInterpolator(new AccelerateDecelerateInterpolator());

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator_1).with(animator_2).with(animator_3).with(animator_4);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
