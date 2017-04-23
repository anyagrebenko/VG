package com.example.user.vg;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class CalculatorActivity extends MainActivity {

//    К А Л Ь К У Л Я Т О Р

    private TextView _screen;
    private String display = "";
    private String currentOperator = "";
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        _screen = (TextView)findViewById(R.id.textView);
        _screen.setText(display);

    }

    private void updateScreen(){
        _screen.setText(display);
    }

    public void onClickNumber(View v){
        if (result != ""){
            clear();
            updateScreen();
        }
        Button b = (Button) v;
        display += b.getText();
        updateScreen();
//        Vibrator vibrator = (Vibrator) this.context.getSystemService(Context.VIBRATOR_SERVICE);
//        vibrator.vibrate(50);
    }

    //    О П Е Р А Т О Р Ы

    private boolean isOperator(char op){
        switch (op){
            case '+':
            case '-':
            case 'x':
            case '÷': return true;
            default: return false;
        }
    }



    public void onClickOperator(View v){
        if (display == "") return;
        Button b = (Button) v;

        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(50);

        if (result != ""){
            String _display = result;
            clear();
            display = _display;
        }

        if(currentOperator != ""){
            Log.d("Calc", "" + display.charAt(display.length()-1));
            if(isOperator(display.charAt(display.length()-1))){
                display = display.replace(display.charAt(display.length()-1), b.getText().toString().charAt(0));
                updateScreen();
                return;
            }else{
                getResult();
                display = result;
                result = "";
            }
            currentOperator = b.getText().toString();
        }
        display += b.getText();
        currentOperator = b.getText().toString();
        updateScreen();
    }

    //    С Т Е Р Е Т Ь

    private void  clear (){
        display = "";
        currentOperator = "";
        result = "";
    }

    public void onClickClear (View v){
        clear();
        updateScreen();

        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(50);
    }

    //    О П Е Р А Т О Р Ы

    private double  operate (String a, String b, String op){
        switch (op){
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-": return Double.valueOf(a) - Double.valueOf(b);
            case "x": return Double.valueOf(a) * Double.valueOf(b);
            case "÷": try {
                return Double.valueOf(a) / Double.valueOf(b);
            }catch (Exception e) {
                Log.d("Calc", e.getMessage());
            }
            default: return -1;
        }

    }

    //    Р Е З У Л Ь Т А Т

    private boolean getResult(){
        if (currentOperator == "") return false;
        String[] operation = display.split(Pattern.quote(currentOperator));
        if (operation.length < 2) return false;
        result = String.valueOf(operate(operation[0], operation[1],currentOperator));
        return true;
    }

    public void onClickEqual (View v) {
        if (display == "") return;
        if (!getResult()) return;
        _screen.setText(display + "\n" + String.valueOf(result));

    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btn0:
//            case R.id.btn1:
//            case R.id.btn2:
//            case R.id.btn3:
//            case R.id.btn4:
//            case R.id.btn5:
//            case R.id.btn6:
//            case R.id.btn7:
//            case R.id.btn8:
//            case R.id.btn9:
//            case R.id.btnPlus:
//            case R.id.btnMinus:
//            case R.id.btnClear:
//            case R.id.btnMult:
//            case R.id.btnDiv:
//            case R.id.btnEqual:
//                vibrator = (Vibrator) getSystemService (VIBRATOR_SERVICE);
//                vibrator.vibrate(50);
//                break;
//        }
//    }
}
