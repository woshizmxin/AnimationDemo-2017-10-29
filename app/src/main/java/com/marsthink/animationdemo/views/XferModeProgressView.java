package com.marsthink.animationdemo.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.marsthink.animationdemo.R;

/**
 * Created by zhoumao on 2017/10/31.
 * Description:
 */

public class XferModeProgressView extends View {

    ValueAnimator animator;

    public XferModeProgressView(Context context) {
        this(context, null);
    }

    public XferModeProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XferModeProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d("jamal.jo", "onAnimationUpdate: radio"+animation.getAnimatedValue());
                invalidate();
            }
        });
    }

    public void startAnimation(){
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radio = (float) animator.getAnimatedValue();
        drawCanvas(canvas, radio);
    }

    public void drawCanvas(Canvas canvas, float radio) {
        //背景部分，也就是上面的图形
        Bitmap background = BitmapFactory.decodeResource(getResources(),
                R.drawable.bg);
        //遮罩，就是提取来部分
        Bitmap mask = BitmapFactory.decodeResource(getResources(),
                R.drawable.front);

        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(background, 0, 0, null);
        Paint paint = new Paint();
        paint.setFilterBitmap(false);

        int x = 0;
        int y = 0;

        // draw the src/dst example into our offscreen bitmap
        int sc = canvas.saveLayer(x, y, x + background.getWidth(), y + background.getHeight(), null,
                Canvas.ALL_SAVE_FLAG);
        // canvas.drawBitmap(mDstB, 0, 0, paint);
        canvas.drawRect(0, 0, background.getWidth() * radio, background
                .getHeight(), paint);
        Log.d("jamal.jo", "drawCanvas: " + background.getWidth() * radio);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // canvas.drawBitmap(mSrcB, 0, 0, paint);
        canvas.drawBitmap(mask, 0f, 0f, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(sc);
    }
}
