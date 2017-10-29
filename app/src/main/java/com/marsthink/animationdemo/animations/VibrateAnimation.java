
package com.marsthink.animationdemo.animations;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class VibrateAnimation extends Animation {
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        t.getMatrix().setTranslate(
                (float) Math.sin(interpolatedTime * 50) * 8,
                (float) Math.sin(interpolatedTime * 50) * 8
        );// 50越大频率越高，8越小振幅越小
        super.applyTransformation(interpolatedTime, t);
    }
}
