package com.android.ui.chapterone.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.android.ui.chapterone.model.PieChartData;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {

    private final String TAG = "Practice11PieChartView";

    private List<PieChartData> pieChartData = new ArrayList<>();
    /**
     * 起始角度
     */
    private float mStartAngle = 0F;
    /**
     * 控件除去Padding后的宽度
     */
    private int mAbsWidth;
    /**
     * 控件除去Padding后的高度
     */
    private int mAbsHeight;
    /**
     * 控件宽度
     */
    private int mWidth;
    /**
     * 控件高度
     */
    private int mHeight;
    /**
     * PaddingStart
     */
    private int paddingStart;
    /**
     * PaddingEnd
     */
    private int paddingEnd;
    /**
     * PaddingTop
     */
    private int paddingTop;
    /**
     * PaddingBottom
     */
    private int paddingBottom;

    private int mBottomTitleHeight;

    private RectF mRectF = new RectF();

    private RectF mSpecialRectF = new RectF();

    private int mCircleRadius;

    private Paint mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint mTitlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float mCircleCenterX;
    private float mCircleCenterY;

    {
        pieChartData.add(new PieChartData("Gingerbread", 0xFF9C27B0, 5, 1));
        pieChartData.add(new PieChartData("IceCreamSandwich", 0xFF9E9E9E, 4, 1));
        pieChartData.add(new PieChartData("Jelly Bean", 0xFF009688, 63, 2));
        pieChartData.add(new PieChartData("KitKat", 0xFF2196F3, 99, 0));
        pieChartData.add(new PieChartData("Lollipop", 0xFFF44336, 123, 0));
        pieChartData.add(new PieChartData("Marshmallow", 0xFFFFC107, 60, 0));
        pieChartData.add(new PieChartData("Froyo", 0, 1, 0));

        mArcPaint.setStyle(Paint.Style.FILL);

        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(2);
        mLinePaint.setColor(Color.WHITE);

        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(20);

        mTitlePaint.setStyle(Paint.Style.STROKE);
        mTitlePaint.setColor(Color.WHITE);
        mTitlePaint.setTextSize(45);
        mTitlePaint.setTextAlign(Paint.Align.CENTER);
    }

    public Practice11PieChartView(Context context) {
        super(context);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    private int mRectPadding = 100;

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
        mBottomTitleHeight = (int) (mAbsHeight * 0.1F);
        mCircleRadius = (mAbsHeight - mBottomTitleHeight) / 2 - mRectPadding;

        float mRectLeft = mAbsWidth * 0.5F - mCircleRadius;
        float mRectTop = (mAbsHeight - mBottomTitleHeight) * 0.5F - mCircleRadius;
        float mRectRight = mAbsWidth * 0.5F + mCircleRadius;
        float mRectBottom = (mAbsHeight - mBottomTitleHeight) * 0.5F + mCircleRadius;

        mRectF.set(mRectLeft, mRectTop, mRectRight, mRectBottom);

        //获取圆心
        mCircleCenterX = (mRectRight + mRectLeft) / 2;
        mCircleCenterY = (mRectBottom + mRectTop) / 2;
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
        //饼图的绘制起始角度
        mStartAngle = 0F;
        //绘制所有饼图
        for (int i = 0; i < pieChartData.size(); i++) {
            //获取数据的值
            PieChartData pieChartData = this.pieChartData.get(i);
            //饼图的角度
            int value = pieChartData.getValue();
            //饼图的Padding角度
            int paddingValue = pieChartData.getPaddingValue();
            //设置要绘制的饼图的颜色
            mArcPaint.setColor(pieChartData.getColor());
            //饼图的绘制停止角度
            float mStopAngle = mStartAngle + value;
            //饼图的绘制中间度
            float mHalfAngle = (mStartAngle + mStopAngle) / 2;
            //如果是要进行特殊绘制的饼图
            if (i != 4) {
                canvas.drawArc(mRectF, mStartAngle, value, true, mArcPaint);

                float mPointStopX = (float) (mCircleCenterX + (mCircleRadius + 50) * Math.cos(mHalfAngle * Math.PI / 180));
                float mPointStopY = (float) (mCircleCenterY + (mCircleRadius + 50) * Math.sin(mHalfAngle * Math.PI / 180));
                float mPointStartX = (float) (mCircleCenterX + (mCircleRadius) * Math.cos(mHalfAngle * Math.PI / 180));
                float mPointStartY = (float) (mCircleCenterY + (mCircleRadius) * Math.sin(mHalfAngle * Math.PI / 180));

                canvas.drawLine(mPointStartX, mPointStartY, mPointStopX, mPointStopY, mLinePaint);

                float mStopXLine = 0;
                if (mHalfAngle >= 270 || mHalfAngle < 90) {
                    mStopXLine = mPointStopX + 100;
                    mTextPaint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText(pieChartData.getTitle(), mStopXLine, mPointStopY, mTextPaint);
                    canvas.drawLine(mPointStopX, mPointStopY, mStopXLine, mPointStopY, mLinePaint);
                } else if (mHalfAngle < 270) {
                    mStopXLine = mPointStopX - 100;
                    mTextPaint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(pieChartData.getTitle(), mStopXLine, mPointStopY, mTextPaint);
                    canvas.drawLine(mPointStopX, mPointStopY, mStopXLine, mPointStopY, mLinePaint);
                }
            } else {
                //计算偏移后的圆心,按照角度的中心线进行偏移
                float mSpecialCircleCenterX = (float) (mCircleCenterX + (25) * Math.cos(mHalfAngle * Math.PI / 180));
                float mSpecialCircleCenterY = (float) (mCircleCenterY + (25) * Math.sin(mHalfAngle * Math.PI / 180));

                mSpecialRectF.set(mSpecialCircleCenterX - mCircleRadius, mSpecialCircleCenterY - mCircleRadius,
                        mSpecialCircleCenterX + mCircleRadius, mSpecialCircleCenterY + mCircleRadius);

                canvas.drawArc(mSpecialRectF, mStartAngle, value, true, mArcPaint);

                float mPointStopX = (float) (mSpecialCircleCenterX + (mCircleRadius + 50) * Math.cos(mHalfAngle * Math.PI / 180));
                float mPointStopY = (float) (mSpecialCircleCenterY + (mCircleRadius + 50) * Math.sin(mHalfAngle * Math.PI / 180));
                float mPointStartX = (float) (mSpecialCircleCenterX + (mCircleRadius) * Math.cos(mHalfAngle * Math.PI / 180));
                float mPointStartY = (float) (mSpecialCircleCenterY + (mCircleRadius) * Math.sin(mHalfAngle * Math.PI / 180));

                canvas.drawLine(mPointStartX, mPointStartY, mPointStopX, mPointStopY, mLinePaint);

                float mStopXLine = 0;
                if (mHalfAngle >= 270 || mHalfAngle < 90) {
                    mStopXLine = mPointStopX + 100;
                    mTextPaint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText(pieChartData.getTitle(), mStopXLine, mPointStopY, mTextPaint);
                    canvas.drawLine(mPointStopX, mPointStopY, mStopXLine, mPointStopY, mLinePaint);
                } else if (mHalfAngle < 270) {
                    mStopXLine = mPointStopX - 100;
                    mTextPaint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(pieChartData.getTitle(), mStopXLine, mPointStopY, mTextPaint);
                    canvas.drawLine(mPointStopX, mPointStopY, mStopXLine, mPointStopY, mLinePaint);
                }
            }
            mStartAngle += value + paddingValue;
        }
        canvas.drawText("饼图", mCircleCenterX, mAbsHeight - mBottomTitleHeight * 0.5F, mTitlePaint);
    }
}
