package com.android.ui.chapterone.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice3DrawRectView extends View {

    private Paint mRectPaint;
    private int mAbsWidth;
    private int mAbsHeight;

    private RectF rectF;
    private int mRectFWidth;
    private int mRectFHeight;

    public Practice3DrawRectView(Context context) {
        super(context);
        init();
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mWidth = measureHandler(widthMeasureSpec);
        int mHeight = measureHandler(heightMeasureSpec);
        int paddingStart = getPaddingStart();
        int paddingTop = getPaddingTop();
        mAbsWidth = mWidth - paddingStart - getPaddingRight();
        mAbsHeight = mHeight - getPaddingTop() - getPaddingBottom();
        setMeasuredDimension(mWidth, mHeight);
        rectF = new RectF();
        mRectFWidth = 430;
        mRectFHeight = 430;
        int mAbsWidthCenter = mAbsWidth / 2;
        int mAbsHeightCenter = mAbsHeight / 2;
        rectF.set(mAbsWidthCenter + paddingStart - mRectFWidth * 0.5F, mAbsHeightCenter + paddingTop - mRectFHeight * 0.5F, mAbsWidthCenter + paddingStart + mRectFWidth * 0.5F, mAbsHeightCenter + paddingTop + mRectFHeight * 0.5F);
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

    private void init() {
        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rectF, mRectPaint);
//        练习内容：使用 canvas.drawRect() 方法画矩形
    }
}
