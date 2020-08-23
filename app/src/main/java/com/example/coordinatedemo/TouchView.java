package com.example.coordinatedemo;

import android.content.Context;
import android.telecom.Call;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

public class TouchView extends View {
    private int x, y;
    int offsetX = 0;
    int offsetY = 0;

    private CallBack mCallBack;

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setCallBack(CallBack callBack) {
        this.mCallBack = callBack;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int last_x = (int) event.getRawX();
        int last_y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = last_x;
                y = last_y;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = last_x - x;
                offsetY = last_y - y;
                layout(getLeft() + offsetX, getTop() + offsetY, getLeft() + offsetX + getWidth(), getTop() + offsetY + getHeight());
                x = last_x;
                y = last_y;
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                break;
            case MotionEvent.ACTION_UP:
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(getWidth(), getHeight());
                params.leftMargin = getLeft();
                params.topMargin = getTop();
                setLayoutParams(params);
                StringBuilder builder = new StringBuilder();
                builder.append("View左边到屏幕左边：");
                builder.append(getLeft());
                builder.append("\n");
                builder.append("手指触点距离view左边：");
                builder.append(event.getX());
                builder.append("\n");
                builder.append("手指出点距离屏幕左边：");
                builder.append(event.getRawX());
                String msg = builder.toString();
                this.mCallBack.response(msg);
                break;
        }
        return true;
    }

}
