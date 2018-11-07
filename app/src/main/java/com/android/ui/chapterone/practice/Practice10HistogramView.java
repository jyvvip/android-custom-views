package com.android.ui.chapterone.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.android.ui.chapterone.model.HistogramData;

import java.util.ArrayList;
import java.util.List;

public class Practice10HistogramView extends View {
    /**
     * 控件的绝对宽度
     */
    private int mAbsWidth;
    /**
     * 控件的绝对高度
     */
    private int mAbsHeight;
    /**
     * 直方图控件的宽度
     */
    private int mHistogramWidth;
    /**
     * 直方图空间的高度
     */
    private int mHistogramHeight;
    /**
     * Padding
     */
    private int paddingStart;
    private int paddingEnd;
    private int paddingTop;
    private int paddingBottom;
    /**
     * Title Height
     */
    private int mBottomTitleHeight;
    /**
     * Text Height
     */
    private int mBottomTextHeight;
    /**
     * 每个直方图之间的Padding
     */
    private int mClassifyPadding;
    /**
     * 直方图Item之间的宽度
     */
    private int mClassifyWidth;
    /**
     * 直方图和Padding之间的比例
     */
    private float mClassifyScale = 0.25F;
    /**
     * 画线的Paint
     */
    private Paint mHistogramLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * Title的Paint
     */
    private Paint mHistogramTitlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * Text的Paint
     */
    private Paint mHistogramTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * 柱状图的Paint
     */
    private Paint mHistogramClassifyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * 数据
     */
    private List<HistogramData> histogramData = new ArrayList<>();

    private int mHeightScaleValue = 200;
    private int mWidth;
    private int mHeight;

    {
        mHistogramLinePaint.setColor(Color.WHITE);
        mHistogramLinePaint.setStrokeWidth(3);

        mHistogramTitlePaint.setColor(Color.WHITE);
        mHistogramTitlePaint.setTextAlign(Paint.Align.CENTER);
        mHistogramTitlePaint.setTextSize(50);

        mHistogramTextPaint.setColor(Color.WHITE);
        mHistogramTextPaint.setTextAlign(Paint.Align.CENTER);
        mHistogramTextPaint.setTextSize(28);

        mHistogramClassifyPaint.setColor(Color.GREEN);
        mHistogramClassifyPaint.setStyle(Paint.Style.FILL);

        histogramData.add(new HistogramData("Froyo", Color.GREEN, 1));
        histogramData.add(new HistogramData("GB", Color.GREEN, 8));
        histogramData.add(new HistogramData("IC S", Color.GREEN, 8));
        histogramData.add(new HistogramData("JB", Color.GREEN, 80));
        histogramData.add(new HistogramData("KitKat", Color.GREEN, 140));
        histogramData.add(new HistogramData("L", Color.GREEN, 170));
        histogramData.add(new HistogramData("M", Color.GREEN, 70));
    }

    public Practice10HistogramView(Context context) {
        super(context);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setPadding(100, 50, 100, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = measureHandler(widthMeasureSpec);
        mHeight = measureHandler(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
        paddingStart = getPaddingStart();
        paddingEnd = getPaddingEnd();
        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();
        mAbsWidth = mWidth - paddingStart - paddingEnd;
        mAbsHeight = mHeight - paddingTop - paddingBottom;
        mBottomTitleHeight = (int) (mAbsHeight * 0.2F);
        mBottomTextHeight = (int) (mAbsHeight * 0.08F);
        mHistogramWidth = mAbsWidth;
        mHistogramHeight = mAbsHeight - mBottomTitleHeight - mBottomTextHeight;

        mClassifyWidth = (int) (mHistogramWidth / (histogramData.size() + (histogramData.size() + 1) * mClassifyScale));
        mClassifyPadding = (int) (mClassifyWidth * mClassifyScale);
    }

    private int measureHandler(int measureSpec) {
        int result = 0;
        int size = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            result = Math.max(size, result);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("直方图", mWidth * 0.5F, mHistogramHeight + mBottomTextHeight + mBottomTitleHeight, mHistogramTitlePaint);

        for (int i = 0; i < histogramData.size(); i++) {
            HistogramData histogramData = this.histogramData.get(i);
            RectF mRectF = new RectF();
            float left = (i + 1) * mClassifyPadding + i * mClassifyWidth + paddingStart;
            float top = mHistogramHeight - histogramData.getValue() * mHistogramHeight / mHeightScaleValue + paddingTop;
            float right = (i + 1) * (mClassifyPadding + mClassifyWidth) + paddingStart;
            float bottom = mHistogramHeight + paddingTop;
            mRectF.set(left, top, right, bottom);
            canvas.drawRect(mRectF, mHistogramClassifyPaint);

            float mTextX = (i + 1) * (mClassifyPadding + mClassifyWidth) + paddingStart - mClassifyWidth * 0.5F;
            float mTextY = mHistogramHeight + paddingTop + mBottomTextHeight * 0.5F;
            canvas.drawText(histogramData.getTitle(), mTextX, mTextY, mHistogramTextPaint);
        }

        canvas.drawLine(paddingStart, paddingTop, paddingStart, mHistogramHeight + paddingTop, mHistogramLinePaint);
        canvas.drawLine(paddingStart, mHistogramHeight + paddingTop, mHistogramWidth + paddingStart, mHistogramHeight + paddingTop, mHistogramLinePaint);

    }
}
