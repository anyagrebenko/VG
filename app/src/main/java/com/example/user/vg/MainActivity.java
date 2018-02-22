package com.example.user.vg;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{

//    Г Л А В Н Ы Й
//    Э К Р А Н


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

//    public void loadWebPage(View v){
//        Intent intent = new Intent(this, WebActivity.class);
//        startActivity(intent);
//    }

    public void OnBtn (View v){
        if(v.getId() == R.id.imagebtnvol){
            Intent i = new Intent(MainActivity.this, SpeechActivity.class);
            startActivity(i);
        }
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(70);
    }

    public void OnGo (View v){
        if (v.getId() == R.id.imagebtncalc){
            Intent i = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(i);
        }
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(70);
    }

    public void OnAbout (View v){
        if (v.getId() == R.id.imagebtnabout){
            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(i);
        }
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(70);
    }

    public void OnSquare (View v) {
        if (v.getId() == R.id.squarebtn){
            Intent i = new Intent(MainActivity.this, SquareActivity.class);
            startActivity(i);
        }
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(70);
    }
}
