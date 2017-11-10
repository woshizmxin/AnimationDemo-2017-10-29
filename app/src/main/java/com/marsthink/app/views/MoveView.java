package com.marsthink.app.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.marsthink.app.utils.ViewUtils;

/**
 * Created by zhoumao on 2017/11/9.
 * Description: 可滑动的button；
 * Link: http://blog.csdn.net/u012193089/article/details/50849189  安卓实现按钮可随意拖动（同时解决click和touch事件的冲突）
 */

public class MoveView extends View {
    boolean isclick;
    int lastX, lastY;
    int screenWidth, screenHeight;

    public MoveView(Context context) {
        this(context, null);
    }

    public MoveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoveView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        screenWidth = ViewUtils.getScreenWidthPixels(getContext());
        screenHeight = ViewUtils.getScreenHeightPixels(getContext());
        setOnTouchListener(mOnTouchListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int ea = event.getAction();
            Log.d("jamal.jo", "onTouch: "+ea);
            switch (ea) {
                case MotionEvent.ACTION_DOWN:
                    isclick = false;//当按下的时候设置isclick为false，具体原因看后边的讲解
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();//按钮初始的横纵坐标
                    break;
                case MotionEvent.ACTION_MOVE:
                    isclick = true;//当按钮被移动的时候设置isclick为true
                    int dx = (int) event.getRawX() - lastX;
                    int dy = (int) event.getRawY() - lastY;//按钮被移动的距离
                    int l = v.getLeft() + dx;
                    int b = v.getBottom() + dy;
                    int r = v.getRight() + dx;
                    int t = v.getTop() + dy;
                    if (l < 0) {//处理按钮被移动到上下左右四个边缘时的情况，决定着按钮不会被移动到屏幕外边去
                        l = 0;
                        r = l + v.getWidth();
                    }
                    if (t < 0) {
                        t = 0;
                        b = t + v.getHeight();
                    }
                    if (r > screenWidth) {
                        r = screenWidth;
                        l = r - v.getWidth();
                    }
                    if (b > screenHeight) {
                        b = screenHeight;
                        t = b - v.getHeight();
                    }
                    v.layout(l, t, r, b);
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    v.postInvalidate();
                    break;
                default:
                    break;
            }
            return isclick;
        }
    };
}
