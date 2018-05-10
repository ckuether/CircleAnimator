package com.activewebsite.circleanimator;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int ANIM_DURATION = 1000;

    CircleAnimView circle1, circle2, circle3;
    TextView circle1_tv, circle2_tv, circle3_tv;

    double score1 = 1.1, score2 = 3.0, score3 = 5.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circle1 = findViewById(R.id.circle1);
        circle2 = findViewById(R.id.circle2);
        circle3 = findViewById(R.id.circle3);
        circle1_tv = findViewById(R.id.circle1_tv);
        circle2_tv = findViewById(R.id.circle2_tv);
        circle3_tv = findViewById(R.id.circle3_tv);

        circle1.setRating(score1);
        circle2.setRating(score2);
        circle3.setRating(score3);

        int angle1 = (int) Math.ceil(score1/5 * 360 * .90);
        int angle2 = (int) Math.ceil(score2/5 * 360 * .90);
        int angle3 = (int) Math.ceil(score3/5 * 360 * .90);

        CircleAngleAnimation animation1 = new CircleAngleAnimation(circle1, angle1);
        CircleAngleAnimation animation2 = new CircleAngleAnimation(circle2, angle2);
        CircleAngleAnimation animation3 = new CircleAngleAnimation(circle3, angle3);
        animation1.setDuration(ANIM_DURATION);
        animation2.setDuration(ANIM_DURATION);
        animation3.setDuration(ANIM_DURATION);
        circle1.startAnimation(animation1);
        circle2.startAnimation(animation2);
        circle3.startAnimation(animation3);

        incrementProximityScore(0, score1, circle1_tv);
        incrementProximityScore(0, score2, circle2_tv);
        incrementProximityScore(0, score3, circle3_tv);
    }

    private void incrementProximityScore(final double startScore, final double ratingValue, final TextView tv){
        //take the duration and create an interval with rate
        double rate = 0.05;
        int delay = (int) (ANIM_DURATION * rate);
        final double interval = ratingValue * rate;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                double preciseScore = startScore + interval;
                double score = Math.round((preciseScore) * 10) / 10.0;
                updateViews(tv, score);
                if(score < ratingValue){
                    incrementProximityScore(preciseScore, ratingValue, tv);
                }
            }
        }, delay-15);
    }

    private void updateViews(TextView tv, double rating){
        tv.setText(Double.toString(rating));
    }
}
