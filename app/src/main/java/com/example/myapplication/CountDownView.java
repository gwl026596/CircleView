package com.example.myapplication;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 进度条View
 */
public class CountDownView extends View implements View.OnClickListener {

    private Paint paint;
    private RectF mTest;
    private float sweepAngle = 0;
    private int time = 5;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            timeTag = true;
            time--;
            invalidate();
            if (time > 1){
                handler.sendEmptyMessageDelayed(0, 1000);
            }
            return false;
        }
    });

    private boolean tag = true;
    private boolean timeTag = true;
    private Rect rect;
    private ValueAnimator animator;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        setOnClickListener(this);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);//取消锯齿
        paint.setStrokeWidth(8);
        rect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTest = new RectF(0, 0, getWidth(), getHeight());
        mTest.inset(paint.getStrokeWidth(), paint.getStrokeWidth());
        paint.setColor(Color.parseColor("#3d000000"));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 2 - 10, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        canvas.drawArc(mTest, -90, sweepAngle * 1.0f, false, paint);
        if (timeTag) {
            paint.setTextSize(70);
            String text = String.valueOf(time);
            paint.getTextBounds(text, 0, text.length(), rect);
            canvas.drawText(text, (getWidth() - rect.width()) / 2, (getHeight() + rect.height()) / 2, paint);
            setPostTime();
        } else {
            paint.setTextSize(70);
            String text = String.valueOf(time);
            paint.getTextBounds(text, 0, text.length(), rect);
            canvas.drawText(text, (getWidth() - rect.width()) / 2, (getHeight() + rect.height()) / 2, paint);
        }
        if (time > 1 && tag) {
            //setAddProgress();
        } else {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void setAddProgress() {
        timeTag = false;
        sweepAngle = sweepAngle + 1.5f;
        invalidate();
    }

    @Override
    public void onClick(View v) {
        sweepAngle = 0;
        time = 5;
        tag = true;
        initAnimator(4000);

    }
    public void initAnimator(int duration) {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
        }
        int start = 0;
        int end = 360;
        handler.sendEmptyMessageDelayed(0, 1000);
        animator = ValueAnimator.ofInt(start, end).setDuration(duration);
        animator.setRepeatCount(0);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                timeTag = false;
                sweepAngle = (int) animation.getAnimatedValue();
                Log.d("衡水市",sweepAngle+"==");
                invalidate();
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }

    private void setPostTime() {

    }
}
