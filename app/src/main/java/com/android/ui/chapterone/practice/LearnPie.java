package com.android.ui.chapterone.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.android.ui.chapterone.model.PieChartData;

import java.util.List;

/**
 * Created by Curry on 2018/11/7.
 */

public class LearnPie extends View {
    private List<PieChartData> data;
    private float mStartAngle;
    private int mAbsWidth,mAbsHeight;
    private int mWidth,mHeight;
    private int paddingStart,paddingEnd,paddingTop,paddingBottom;
    private int mBottomTitleHeight;
    private RectF mRectF,mSpecialRectF;
    private int mCircleRadius;
    private float mCircleCenterX,mCircleCenterY;
    private Paint mArcP,mLineP,mTextP,mTitleP;
    private int mRectPadding=100;

    public LearnPie(Context context){
        super(context);
        init();
    }

    public LearnPie(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        init();
    }

    public LearnPie(Context context,AttributeSet attributeSet,int defStyleAttr){
        super(context,attributeSet,defStyleAttr);
        init();
    }

    private void init(){
        data.add(new PieChartData("Gingerbread", 0xFF9C27B0, 5, 1));
        data.add(new PieChartData("IceCreamSandwich", 0xFF9E9E9E, 4, 1));
        data.add(new PieChartData("Jelly Bean", 0xFF009688, 63, 2));
        data.add(new PieChartData("KitKat", 0xFF2196F3, 99, 0));
        data.add(new PieChartData("Lollipop", 0xFFF44336, 123, 0));
        data.add(new PieChartData("Marshmallow", 0xFFFFC107, 60, 0));
        data.add(new PieChartData("Froyo", 0, 1, 0));

        mArcP=new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcP.setStyle(Paint.Style.FILL);

        mLineP=new Paint(Paint.ANTI_ALIAS_FLAG);
        mLineP.setStyle(Paint.Style.STROKE);
        mLineP.setStrokeWidth(2);
        mLineP.setColor(Color.WHITE);

        mTextP=new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextP.setStyle(Paint.Style.STROKE);
        mTextP.setColor(Color.WHITE);
        mTextP.setTextSize(20);

        mTextP=new Paint(Paint.ANTI_ALIAS_FLAG);
        mTitleP.setStyle(Paint.Style.STROKE);
        mTitleP.setColor(Color.WHITE);
        mTitleP.setTextSize(45);
        mTitleP.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth=measureHandler(widthMeasureSpec);
        mHeight=measureHandler(heightMeasureSpec);
        setMeasuredDimension(mWidth,mHeight);

        mAbsWidth=mWidth-getPaddingStart()-getPaddingEnd();
        mAbsHeight=mHeight-getPaddingTop()-getPaddingBottom();
        mBottomTitleHeight=(int)(mAbsHeight/10F);
        mCircleRadius=(mAbsHeight-mBottomTitleHeight)/2-mRectPadding;

        float rl=mAbsWidth*0.5F-mCircleRadius;
        float rr=mAbsHeight*0.5F+mCircleRadius;
        float rt=(mAbsHeight-mBottomTitleHeight)*0.5F-mCircleRadius;
        float rb=(mAbsHeight-mBottomTitleHeight)*0.5F+mCircleRadius;
        mRectF.set(rl,rt,rr,rb);

        mCircleCenterX=(rl+rr)/2F;
        mCircleCenterY=(rt+rb)/2F;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mStartAngle=0F;
        for (int i=0;i<data.size();i++){
            PieChartData pie=data.get(i);
            int value=pie.getValue();
            int padValue=pie.getPaddingValue();
            int color=pie.getColor();
            String text=pie.getTitle();

            mArcP.setColor(color);
            float stopAngle=mStartAngle+value;
            float halfAngle=(mStartAngle+mStartAngle)/2;

            if (i!=4){
                canvas.drawArc(mRectF,mStartAngle,value,true,mArcP);
                float stopX=(float)(mCircleCenterX+(mCircleRadius+50)*Math.cos(halfAngle*Math.PI/180));
                float stopY=(float)(mCircleCenterY+(mCircleRadius+50)*Math.sin(halfAngle*Math.PI/180));
                float startX=(float)(mCircleCenterX+(mCircleRadius)*Math.cos(halfAngle*Math.PI/180));
                float startY=(float)(mCircleCenterY+(mCircleRadius)*Math.sin(halfAngle*Math.PI/180));

                canvas.drawLine(startX,startY,stopX,stopY,mLineP);

                float stopXLine=0;
                if (halfAngle>=270||halfAngle<90){
                    stopXLine=stopX+100;
                    mTextP.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText(text,stopXLine,stopY,mLineP);
                    canvas.drawLine(stopX,stopY,stopXLine,stopY,mLineP);
                }


            }




        }














    }

    private int measureHandler(int measureSpec){
        int result=0;
        int size=MeasureSpec.getSize(measureSpec);
        int mode=MeasureSpec.getMode(measureSpec);
        if (mode==MeasureSpec.EXACTLY){
            result=size;
        }else if (mode==MeasureSpec.AT_MOST){
            result=Math.max(result,size);
        }
        return result;
    }


}
