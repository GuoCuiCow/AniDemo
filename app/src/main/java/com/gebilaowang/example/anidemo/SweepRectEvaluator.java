package com.gebilaowang.example.anidemo;

import android.animation.TypeEvaluator;


/**
 * Created by cuiguo on 2016/11/22.
 */

public class SweepRectEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float v, Object o, Object t1) {
        SweepRect startRect = (SweepRect) o;
        SweepRect endRect = (SweepRect) t1;
        float x = startRect.getTop() + v * (endRect.getTop() - startRect.getTop());
        float y = startRect.getBottom() + v * (endRect.getBottom() - startRect.getBottom());
        SweepRect sweepRect = new SweepRect(startRect.getLeft(),x,startRect.getRight(), y);
        return sweepRect;
    }
}
