package com.gebilaowang.example.anidemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private SweepView mSweepView;
    private Button mButton;
    private Button mButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.buttonPanel);
        mButton1 = (Button) findViewById(R.id.button1);
        Path path = new Path();
        path.lineTo(0,300);
        path.cubicTo(100, 0, 300, 900, 500, 600);


        PathInterpolator pathInterpolator = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            pathInterpolator = new PathInterpolator(0.8f, 0f, 1f, 1f);

        final ObjectAnimator mAnimator = ObjectAnimator.ofFloat(mButton, View.X, View.Y, path);
        mAnimator.setInterpolator(pathInterpolator);
        mAnimator.setDuration(3000);
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        mAnimator.start();
        }
        mSweepView = (SweepView) findViewById(R.id.sweepView);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonPanel:
                mSweepView.start();
                break;
            case R.id.button1:
                mSweepView.stop();
                break;
        }


    }
}
