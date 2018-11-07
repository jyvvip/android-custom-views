package com.android.ui.chapterone.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice4DrawPointView extends View {

    private int mAbsWidth;
    private int mAbsHeight;
    private Paint mRoundPointPaint;
    private Paint mSquarePointPaint;

    public Practice4DrawPointView(Context context) {
        super(context);
        init();
    }

    public Practice4DrawPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice4DrawPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mRoundPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRoundPointPaint.setColor(Color.BLACK);
        mRoundPointPaint.setStrokeWidth(80);
        mRoundPointPaint.setStrokeCap(Paint.Cap.ROUND);

        mSquarePointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSquarePointPaint.setColor(Color.BLACK);
        mSquarePointPaint.setStrokeWidth(80);
        mSquarePointPaint.setStrokeCap(Paint.Cap.SQUARE);

        setPadding(150,0,150,0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mWidth = measureHandler(widthMeasureSpec);
        int mHeight = measureHandler(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
        int paddingStart = getPaddingStart();
        int paddingEnd = getPaddingEnd();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        mAbsWidth = mWidth - paddingStart - paddingEnd;
        mAbsHeight = mHeight - paddingTop - paddingBottom;
    }

    private int measureHandler(int measureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == MeasureSpec.AT_MOST){
            result = Math.max(size, result);
        }
        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        练习内容：使用 canvas.drawPoint() 方法画点
//        一个圆点，一个方点
//        圆点和方点的切换使用 paint.setStrokeCap(cap)：`ROUND` 是圆点，`BUTT` 或 `SQUARE` 是方点
        canvas.drawPoint(mAbsWidth * 0.25F + getPaddingStart(), mAbsHeight * 0.5F + getPaddingTop(), mRoundPointPaint);
        canvas.drawPoint(mAbsWidth * 0.75F + getPaddingStart(), mAbsHeight * 0.5F + getPaddingTop(), mSquarePointPaint);
    }
}
