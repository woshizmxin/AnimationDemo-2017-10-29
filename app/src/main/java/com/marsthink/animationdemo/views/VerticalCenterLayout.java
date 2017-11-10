package com.marsthink.animationdemo.views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public class VerticalCenterLayout extends ViewGroup {

    private Context mContext;
    private int sreenH;

    public VerticalCenterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        // 获取屏幕的高度  
        sreenH = getScreenSize(((Activity) mContext))[1];
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        // 测量子View  
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 获得子View个数  
        int childCount = getChildCount();
        // 设置一个变量保存到父View左侧的距离  
        int mLeft = 0;
        // 遍历子View  
        for (int i = 0; i < childCount; i++) {

            View childView = getChildAt(i);
            // 获得子View的高度  
            int childViewHeight = childView.getMeasuredHeight();
            // 获得子View的宽度  
            int childViewWidth = childView.getMeasuredWidth();
            // 让子View在竖直方向上显示在屏幕的中间位置  
            int height = sreenH / 2 - childViewHeight / 2;
            // 调用layout给每一个子View设定位置mLeft,mTop,mRight,mBottom.左上右下  
            childView.layout(mLeft, height, mLeft + childViewWidth, height
                    + childViewHeight);
            // 改变下一个子View到父View左侧的距离  
            mLeft += childViewWidth;
        }
    }

    /**
     * 获取屏幕尺寸
     */
    public static int[] getScreenSize(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return new int[]{metrics.widthPixels, metrics.heightPixels};
    }
}  
