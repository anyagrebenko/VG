package com.example.user.vg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity{

//    Г Л А В Н Ы Й
//    Э К Р А Н

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

    }

    public void OnBtn (View v){
        if(v.getId() == R.id.btngo2){
            Intent i = new Intent(MainActivity.this, SpeechActivity.class);
            startActivity(i);
        }
    }

    public void OnGo (View v){
        if (v.getId() == R.id.btngo){
            Intent i = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(i);
        }
    }

    public void OnAbout (View v){
        if (v.getId() == R.id.btngo3){
            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(i);
        }
    }

}
