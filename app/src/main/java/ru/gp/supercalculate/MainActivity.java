package ru.gp.supercalculate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    MaterialButton settButton;
    MaterialButton one, two, three, four, five, six, seven, eight, nine, zero, point, equal, plus, minus, multiply, divide, percent, clear, delete;
    TextView editTextTextPersonName, textView2;
    private String input, output, newOutput;
    double finalResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settButton = findViewById(R.id.settingButton);


        Animation1();
        initBut();
        clickBut();

        settButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                recreate();

            }
        });


    }

    private void clickBut() {
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        point.setOnClickListener(this);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
        divide.setOnClickListener(this);
        delete.setOnClickListener(this);
        multiply.setOnClickListener(this);
        equal.setOnClickListener(this);
        percent.setOnClickListener(this);
        clear.setOnClickListener(this);


    }

    private void initBut() {
        textView2 = findViewById(R.id.textView2);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        point = findViewById(R.id.point);
        equal = findViewById(R.id.equal);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        percent = findViewById(R.id.percent);
        delete = findViewById(R.id.delete);
        clear = findViewById(R.id.clear);

    }

    private void Animation1() {
        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();


    }


    // Все инициализации кнопок очень похожи, поэтому создадим метод для переиспользования
    private void initRadioButton(View button, final int codeStyle) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// сохраним настройки
                setAppTheme(codeStyle);
// пересоздадим активити, чтобы тема применилась
                recreate();
            }
        });
    }

    @Override
    public void onClick(View view) {
        MaterialButton materialButton = (MaterialButton) view;
        String data = materialButton.getText().toString();
        switch (data) {
            case "AC":
                input = null;
                output = null;
                newOutput = null;
                editTextTextPersonName.setText("");
                break;

            case "^":
                solve();
                input += "^";
                break;
            case "˟":
                solve();
                input += "˟";
                break;
            case "=":
                solve();
                break;
            case "%":
                input += "%";
                double d = Double.parseDouble(textView2.getText().toString()) / 100;
                editTextTextPersonName.setText(String.valueOf(d));
                break;

            default:
                if (input == null) {
                    input = "";
                }
                if (data.equals("+") || data.equals("÷") || data.equals("-")) {
                    solve();
                }
                input += data;
        }
        textView2.setText(input);
    }

    private void solve() {
        if (input.split("\\+").length == 2) {
            String numbers[] = input.split("\\+");
            try {
                double d = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                editTextTextPersonName.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                editTextTextPersonName.setError(e.getMessage().toString());
            }
        }
        if (input.split("\\˟").length == 2) {
            String numbers[] = input.split("\\˟");
            try {
                double d = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                editTextTextPersonName.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                editTextTextPersonName.setError(e.getMessage().toString());
            }
        }
        if (input.split("\\÷").length == 2) {
            String numbers[] = input.split("\\÷");
            try {
                double d = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                editTextTextPersonName.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                editTextTextPersonName.setError(e.getMessage().toString());
            }
        }
        if (input.split("\\^").length == 2) {
            String numbers[] = input.split("\\^");
            try {
                double d = Math.pow(Double.parseDouble(numbers[0]),  Double.parseDouble(numbers[1]));
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                editTextTextPersonName.setText(newOutput);
                input = d +"";
            }catch (Exception e){
                editTextTextPersonName.setError(e.getMessage().toString());
            }
        }
        if (input.split("\\-").length == 2) {
            String numbers[] = input.split("\\-");
            try {
                if (Double.parseDouble(numbers[0]) < Double.parseDouble(numbers[1])){
                    double d = Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[0]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    editTextTextPersonName.setText("-" + newOutput);
                    input = d +"";
                }
                else {
                    double d = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    editTextTextPersonName.setText(newOutput);
                    input = d + "";
                }
            }catch (Exception e){
                editTextTextPersonName.setError(e.getMessage().toString());
            }
        }
    }
    private String cutDecimal (String number){
        String n [] = number.split("\\.");
        if (n.length >1){
            if (n[1].equals("0")){
                number = n[0];
            }
        }
        return number;
    }
}
