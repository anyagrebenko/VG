package com.example.user.vg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SquareActivity extends MainActivity {

    private EditText a_text;
    private EditText b_text;
    private EditText c_text;
    private TextView textView;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square);
        a_text = (EditText) findViewById(R.id.editText);
        b_text = (EditText) findViewById(R.id.editText2);
        c_text = (EditText) findViewById(R.id.editText3);
        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
    }

    public void ClickButton(View v){
        //объявляем переменные
        double a, b, c, D;

        //считываем значения
        String S1 = a_text.getText().toString();
        String S2 = b_text.getText().toString();
        String S3 = c_text.getText().toString();

        //преобразовываем текстовые переменные в числовые значения
        a = Double.parseDouble(S1);
        b = Double.parseDouble(S2);
        c = Double.parseDouble(S3);

        //проводим числовыве вычисления
        D = b * b - 4 * a * c;
        if (D > 0) {
            double x1, x2;
            x1 = (-b - Math.sqrt(D)) / (2 * a);
            x2 = (-b + Math.sqrt(D)) / (2 * a);
            //преобразовываем ответ в число
            String S4 = Double.toString(x1);
            String S5 = Double.toString(x2);

            //выведем текст в textView
            textView.setText(S4);
            textView1.setText(S5);

        } else if (D == 0) {
            double x;
            x = -b / (2 * a);
            //преобразовываем ответ в число
            String S = Double.toString(x);

            //выведем текст в textView
            textView.setText(S);
        } else {
            //преобразовываем ответ в число
            String Stxt = "Уравнение не имеет действительных корней";
            //выведем текст в textView
            textView.setText(Stxt);
        }
    }

    public void OnClear (){
        a_text.setText(null);
        b_text.setText(null);
        c_text.setText(null);
        textView.setText(null);
        textView1.setText(null);
    }

}
