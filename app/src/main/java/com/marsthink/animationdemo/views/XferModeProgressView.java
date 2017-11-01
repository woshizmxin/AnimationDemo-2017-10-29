package com.marsthink.animationdemo.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.marsthink.animationdemo.R;

/**
 * Created by zhoumao on 2017/10/31.
 * Description:
 */

public class XferModeProgressView extends View {

    ValueAnimator animator;
    Bitmap background;
    Bitmap mask;
    Paint paint;

    public XferModeProgressView(Context context) {
        this(context, null);
    }

    public XferModeProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XferModeProgressView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

        animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(1000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });
        //背景部分，也就是上面的图形
        background = BitmapFactory.decodeResource(getResources(),
                R.drawable.bg);
        //遮罩，就是提取来部分
        mask = BitmapFactory.decodeResource(getResources(),
                R.drawable.front);
        paint = new Paint();
    }

    public void startAnimation() {
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radio = (float) animator.getAnimatedValue();
        drawCanvas(canvas, radio);
    }
    /*
     * MeasureSpec.EXACTLY是精确尺寸，当我们将控件的layout_width或layout_height指定为具体数值时如andorid:layout_width="50dip"，或者为FILL_PARENT是，都是控件大小已经确定的情况，都是精确尺寸。
     *
     * MeasureSpec.AT_MOST是最大尺寸，当控件的layout_width或layout_height指定为WRAP_CONTENT时，控件大小一般随着控件的子空间或内容进行变化，此时控件尺寸只要不超过父控件允许的最大尺寸即可。
     * 因此，此时的mode是AT_MOST，size给出了父控件允许的最大尺寸。
     *
     *MeasureSpec.UNSPECIFIED是未指定尺寸，这种情况不多，一般都是父控件是AdapterView，通过measure方法传入的模式。
     **/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width, height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = (int) (getPaddingTop() + background.getWidth() + getPaddingBottom());
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = (int) (getPaddingTop() + background.getHeight() + getPaddingBottom());
        }
        setMeasuredDimension(width, height);
    }

    public void drawCanvas(Canvas canvas, float radio) {
        canvas.drawBitmap(background, 0, 0, null);
        paint.setFilterBitmap(false);
        int sc = canvas.saveLayer(0, 0, background.getWidth(), background.getHeight(), null,
                Canvas.ALL_SAVE_FLAG);
        canvas.drawRect(0, 0, background.getWidth() * radio, background
                .getHeight(), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mask, 0f, 0f, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(sc);
    }
}
