package com.seerauberstudios.creditcheetah;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;


public class SplashActivity extends Activity {

    private static final int DELAY = 2000; // 1s

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView catAnimView = (ImageView)findViewById(R.id.splash_cat_logo);
        final ImageView cardAnimView = (ImageView)findViewById(R.id.splash_card_logo);


        RotateAnimation anim1 = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        RotateAnimation anim2 = new RotateAnimation(360.0f,0.0f , Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);


        anim1.setInterpolator(new LinearInterpolator());
        anim1.setRepeatCount(Animation.INFINITE);
        anim1.setDuration(700);

        anim2.setInterpolator(new LinearInterpolator());
        anim2.setRepeatCount(Animation.INFINITE);
        anim2.setDuration(700);

        //Start animation
        catAnimView.startAnimation(anim1);
        cardAnimView.startAnimation(anim2);



        //

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                catAnimView.setAnimation(null);
                cardAnimView.setAnimation(null);
                startMainActivity();
            }
        }, DELAY);


    }

    private void startMainActivity() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }


}
