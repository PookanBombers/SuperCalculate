package ru.gp.supercalculate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public abstract class BaseActivity extends AppCompatActivity {



    // Имя настроек
    private static final String NameSharedPreference = "LOGIN";
    // Имя параметра в настройках
    private static final String themeSuperCalculate = "THEME_SUPER_CALCULATE";
    protected static final int MyStandard = 0;
    protected static final int MyOcean = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Устанавливать тему надо только до установки макета активити
        setTheme(getAppTheme(R.style.Standard_Theme));
        super.onCreate(savedInstanceState);


    }



    protected int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }
    // Чтение настроек, параметр «тема»
    protected int getCodeStyle(int codeStyle){
// Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                MODE_PRIVATE);
//Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(themeSuperCalculate, codeStyle);
    }
    // Сохранение настроек
    protected void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                MODE_PRIVATE);
// Настройки сохраняются посредством специального класса editor
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(themeSuperCalculate, codeStyle);
        editor.apply();
    }
    protected int codeStyleToStyleId(int codeStyle){
        switch(codeStyle){
            case MyOcean:
                return R.style.Ocean_Theme;
            default:
                return R.style.Standard_Theme;
        }
    }

}
