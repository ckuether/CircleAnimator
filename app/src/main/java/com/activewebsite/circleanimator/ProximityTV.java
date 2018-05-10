package com.activewebsite.circleanimator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class ProximityTV extends android.support.v7.widget.AppCompatTextView {

    public ProximityTV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int width, int height){
        setMeasuredDimension(width, width);
    }
}
