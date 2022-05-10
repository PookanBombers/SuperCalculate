package ru.gp.supercalculate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation1();
        initThemeChooser();

    }

    private void Animation1() {
        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
    }
    // Инициализация радиокнопок
    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.standard),
                MyStandard);
        initRadioButton(findViewById(R.id.ocean),
                MyOcean);
        RadioGroup rg = findViewById(R.id.radioButtons);
        ((MaterialRadioButton)rg.getChildAt(getCodeStyle(MyStandard))).setChecked(true);
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

}