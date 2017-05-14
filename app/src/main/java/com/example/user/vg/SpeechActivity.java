package com.example.user.vg;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

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

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    public void onActivityResult(int request_code , int result_code, Intent i) {
        super.onActivityResult(request_code , result_code , i);

            switch (request_code) {
                case 100:
                        if (result_code == RESULT_OK && i != null) {
//                            try{
                            ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                            String s = result.get(0);
                            s = s.replaceAll("на", "");
                            s = s.replaceAll("к", "");
                            s = s.replaceAll("разделить", "/");
                            s = s.replaceAll("делить", "/");
                            s = s.replaceAll("отнять", "-");
                            s = s.replaceAll("прибавить", "+");
                            s = s.replaceAll("сложить", "+");
                            s = s.replaceAll("вычесть", "-");
                            s = s.replaceAll("умножить", "*");

                            resultTEXT.setText(s + " = " + eval(s) + "");
//                            throw new IOException();
//                            } catch (IOException e) {
//                                Toast toast = Toast.makeText(getApplicationContext(), "ERROR! Try Again.", Toast.LENGTH_SHORT);
//                                toast.show();
//                            }
                        }
                    break;

            }
        }
}

