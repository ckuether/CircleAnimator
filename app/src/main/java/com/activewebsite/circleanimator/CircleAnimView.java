package com.activewebsite.circleanimator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CircleAnimView extends View {

    private static final int START_ANGLE_POINT = 90;

    private Paint paint;
    private RectF rect;

    final int strokeWidth = 80;

    private float centerCircleX;
    private float centerCircleY;
    private float rectWidth;

    private double rating = 0;

    private float angle;

    public CircleAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);

        paint.setColor(Color.RED);

        //size 200x200 example
        rect = new RectF(strokeWidth, strokeWidth, 200 + strokeWidth, 200 + strokeWidth);

        //Initial Angle (optional, it can be zero)
        angle = 0;

    }

    @Override
    protected void onMeasure(int width, int height){
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        centerCircleX = w/2 + rectWidth/2;
        centerCircleY = h/2 + rectWidth/2;
        rectWidth = w - strokeWidth;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        rect.set(strokeWidth/2, strokeWidth/2, rectWidth + strokeWidth/2, rectWidth + strokeWidth/2);

        if(rating < 2)
            paint.setColor(Color.RED);
        else if(rating < 4)
            paint.setColor(Color.YELLOW);
        else
            paint.setColor(Color.GREEN);

        canvas.rotate(21, centerCircleX, centerCircleY);
        canvas.drawArc(rect, START_ANGLE_POINT, angle, false, paint);
    }

    public float getAngle(){
        return angle;
    }

    public void setAngle(float angle){
        this.angle = angle;
    }

    public void setRating(double r){
        rating = r;
    }
}
