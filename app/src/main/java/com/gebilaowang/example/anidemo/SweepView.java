package com.gebilaowang.example.anidemo;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cuiguo on 2016/11/22.
 */

public class SweepView extends View implements Animator.AnimatorListener{



    private int den=15;
    SweepRect currentRect;
    private Paint mPaint;
    private ValueAnimator mAnim=null;


    public SweepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStrokeWidth((float) 2.0);
        mPaint.setColor(Color.WHITE);
    }
    public void setDensity(int density) {
        this.den = density;
    }
    public void start(){
        startAnimation();
    }
    public void stop(){
        if (mAnim!=null){
            mAnim.end();
        }
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawThickLines(canvas);
        drawThinLines(canvas);
        if (currentRect == null) {
            currentRect = new SweepRect(0, 0, getWidth(),  getHeight()/5);
            drawRect(canvas);
//            startAnimation();
        } else {
            drawRect(canvas);
        }


    }

    private void drawThickLines(Canvas canvas){
        canvas.drawColor(Color.TRANSPARENT);                  //白色背景
        mPaint.setStrokeWidth((float) 3.0);
        mPaint.setShader(null);
        mPaint.setColor(getResources().getColor(R.color.white));
        canvas.drawLines(getPts(canvas.getWidth(),canvas.getHeight(),den), mPaint);
//        canvas.drawLines(getHightPts(canvas.getWidth(),canvas.getHeight(),hightpts), mPaint);

    }
    private void drawThinLines(Canvas canvas){
        canvas.drawColor(Color.TRANSPARENT);                  //白色背景
        mPaint.setStrokeWidth((float) 1.0);
        mPaint.setShader(null);
        mPaint.setColor(getResources().getColor(R.color.white));
        canvas.drawLines(getPts(canvas.getWidth(),canvas.getHeight(),den*4), mPaint);

    }
    private void drawRect(Canvas canvas){
        mPaint.setColor(getResources().getColor(R.color.color_b11));

        LinearGradient lg=new LinearGradient(0,currentRect.getTop(),0,currentRect.getBottom(),Color.TRANSPARENT,getResources().getColor(R.color.color_b11), Shader.TileMode.CLAMP);
        mPaint.setShader(lg);
        canvas.drawRect(currentRect.getLeft(),currentRect.getTop(), currentRect.getRight(), currentRect.getBottom(), mPaint);

    }
    private float[] getPts(int width,int hight,int density){
        int widthGap=width/density;
        int j =hight/widthGap+1;
        float[] pts = new float[(density+j)*4];

        for (int i=1;i<density;i++){
            pts[i*4]=i*widthGap;
            pts[i*4+1]=0;
            pts[i*4+2]=i*widthGap;
            pts[i*4+3]=hight;
        }
        for (int i=density;i<density+j;i++){
            pts[i*4]=0;
            pts[i*4+1]=(i-density)*widthGap;
            pts[i*4+2]=width;
            pts[i*4+3]=(i-density)*widthGap;
        }
        return  pts;
    }

    private void startAnimation() {

        final SweepRect startRect =new SweepRect(0, -getHeight()/5, getWidth(),  0);
        final SweepRect endRect =new SweepRect(0, getHeight(), getWidth(),  getHeight()*6/5);

        mAnim = ValueAnimator.ofObject(new SweepRectEvaluator(), startRect,endRect);

        mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentRect = (SweepRect) animation.getAnimatedValue();
                invalidate();
            }
        });
        mAnim.addListener(this);
        mAnim.setDuration(2000);
        mAnim.setRepeatCount(-1);
        mAnim.setRepeatMode(ValueAnimator.RESTART);
        mAnim.start();
    }

    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {

    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }
}
