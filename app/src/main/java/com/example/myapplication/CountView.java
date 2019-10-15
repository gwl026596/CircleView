package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 计数器View
 */
public class CountView extends View implements View.OnClickListener {

    private Paint paint;
    private Rect mTest;
    private int count=0;

    public CountView(Context context) {
        this(context,null);
    }

    public CountView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        setOnClickListener(this);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTest = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLUE);
        canvas.drawRect(0,0,getWidth(),getHeight(),paint);
        paint.setColor(Color.RED);
        paint.setTextSize(sp2px(getContext(),28));
        String text=String.valueOf(count);
        paint.getTextBounds(text,0,text.length(),mTest);
        canvas.drawText(text,(getWidth()-mTest.width())/2,(getHeight()+mTest.height())/2,paint);
    }

    @Override
    public void onClick(View v) {
        count++;
        invalidate();
    }

    // 将sp值转换为px值，保证文字大小不变
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
