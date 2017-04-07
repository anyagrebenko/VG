package com.example.user.vg;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by user on 07.04.2017.
 */

public class SpeechActivity extends MainActivity {

//    Г О Л О С О В О Й
//    Э К Р А Н

    private TextView resultTEXT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
        resultTEXT = (TextView)findViewById(R.id.textresult);
    }

    public void OnButtonClick (View v){
        if (v.getId() == R.id.imageButton){
            promptSpeechInput();
        }
    }

    public void promptSpeechInput(){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL , RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE , Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT , "Say something!");

        try {
            startActivityForResult(i, 100);
        }
        catch (ActivityNotFoundException a){
            Toast.makeText(SpeechActivity.this, "Sorry! Your device doesn't support speech languages!" ,
                    Toast.LENGTH_LONG).show();
        }
    }

    public void onActivityResult(int request_code , int result_code, Intent i){
        super.onActivityResult(request_code , result_code , i);

        switch (request_code){
            case 100: if(result_code == RESULT_OK && i != null){
                ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                resultTEXT.setText(result.get(0));
            }
            break;
        }
    }
}
