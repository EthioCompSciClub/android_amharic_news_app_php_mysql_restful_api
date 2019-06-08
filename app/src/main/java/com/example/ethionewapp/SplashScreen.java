package com.example.ethionewapp;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private TextView textview;
    private TextView bytextview;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        textview = findViewById(R.id.et_textview);
        bytextview = findViewById(R.id.et_by_text);
        image  = findViewById(R.id.et_image);

        Animation transition = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mytransition);
        textview.startAnimation(transition);
        Animation rtol = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rtol);
        bytextview.startAnimation(rtol);
        Init();
    }

    public void Init(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }
}
