package com.android.ui.chapterone.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {

    private Paint mCircle4Paint;
    private Paint mCircle3Paint;
    private Paint mCircle2Paint;
    private Paint mCircle1Paint;
    private int mCircleRadius;
    private int mAbsWidth;
    private int mAbsHeight;
    private int paddingTop;
    private int paddingStart;

    public Practice2DrawCircleView(Context context) {
        super(context);
        init();
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCircle1Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircle1Paint.setColor(Color.BLACK);
        mCircle2Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircle2Paint.setColor(Color.BLACK);
        mCircle2Paint.setStyle(Paint.Style.STROKE);
        mCircle3Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircle3Paint.setColor(Color.BLUE);
        mCircle4Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircle4Paint.setStyle(Paint.Style.STROKE);
        mCircle4Paint.setStrokeWidth(20);
        setPadding(100,0,100,0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mWidth = measureHandler(widthMeasureSpec);
        int mHeight = measureHandler(heightMeasureSpec);
        paddingStart = getPaddingStart();
        paddingTop = getPaddingTop();
        mAbsWidth = mWidth - paddingStart - getPaddingRight();
        mAbsHeight = mHeight - paddingTop - getPaddingBottom();
        //init Circle radius
        int mCirclePadding = 10;
        mCircleRadius = Math.min(mAbsHeight, mAbsWidth) / 4 - mCirclePadding;
        setMeasuredDimension(mWidth, mHeight);
    }

    private int measureHandler(int measureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = Math.max(size, result);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
        canvas.drawCircle(mAbsWidth * 0.25F + paddingStart, mAbsHeight * 0.25F + paddingTop, mCircleRadius, mCircle1Paint);
        canvas.drawCircle(mAbsWidth * 0.75F + paddingStart, mAbsHeight * 0.25F + paddingTop, mCircleRadius, mCircle2Paint);
        canvas.drawCircle(mAbsWidth * 0.25F + paddingStart, mAbsHeight * 0.75F + paddingTop, mCircleRadius, mCircle3Paint);
        canvas.drawCircle(mAbsWidth * 0.75F + paddingStart, mAbsHeight * 0.75F + paddingTop, mCircleRadius, mCircle4Paint);
    }
}
