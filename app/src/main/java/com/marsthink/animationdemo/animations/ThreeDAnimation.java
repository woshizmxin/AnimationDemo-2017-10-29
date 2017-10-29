package com.marsthink.animationdemo.animations;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Transformation;

public class ThreeDAnimation extends Animation {

    private int mCenterWidth, mCenterHeight;
    private Camera mCamera = new Camera();
    private float mRotateY = 0.0f;

    // 一般在此方法初始化一些动画相关的变量和值
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        // 设置默认时长
        setDuration(4000);
        // 保持动画的结束状态
        setFillAfter(true);
        // 设置默认插值器
        setInterpolator(new BounceInterpolator());// 回弹效果的插值器
        mCenterWidth = width / 2;
        mCenterHeight = height / 2;
    }

    // 暴露接口设置旋转角度
    public void setRotateY(float rotateY) {
        mRotateY = rotateY;
    }

    // 自定义动画的核心，在动画的执行过程中会不断回调此方法，并且每次回调interpolatedTime值都在不断变化(0----1)
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        final Matrix matrix = t.getMatrix();
        mCamera.save();
        // 使用Camera设置Y轴方向的旋转角度
        mCamera.rotateX(mRotateY * interpolatedTime);
        // 将旋转变化作用到matrix上
        mCamera.getMatrix(matrix);
        // 由于旋转是以(0,0)为中心的,所以为了把界面的中心与(0,0)对齐,就要preTranslate(-centerX, -centerY),
        // rotateY完成后,调用postTranslate(centerX, centerY),再把图片移回来,这样看到的动画效果就是activity的界面图片从中心不停的旋转了。

        matrix.preTranslate(-mCenterWidth, -mCenterHeight);// 在旋转之前开始位移动画
        matrix.postTranslate(mCenterWidth, mCenterHeight);// 在旋转之后开始位移动画
        mCamera.restore();
    }
}
