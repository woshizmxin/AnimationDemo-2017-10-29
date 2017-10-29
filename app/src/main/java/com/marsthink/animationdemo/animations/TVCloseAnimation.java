package com.marsthink.animationdemo.animations;

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 通过矩阵变换模拟电视关闭效果，使图片的纵向比例不断缩小
 * Created by wondertwo on 2016/3/13.
 */
public class TVCloseAnimation extends Animation {

    private int mCenterWidth, mCenterHeight;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        // 设置默认时长
        setDuration(4000);
        // 保持动画的结束状态
        setFillAfter(true);
        // 设置默认插值器
        // setInterpolator(new BounceInterpolator());// 回弹效果的插值器
        mCenterWidth = width / 2;
        mCenterHeight = height /2;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        final Matrix matrix = t.getMatrix();
        matrix.preScale(1,
                        1 - interpolatedTime,
                        mCenterWidth,
                        mCenterHeight);
    }
}