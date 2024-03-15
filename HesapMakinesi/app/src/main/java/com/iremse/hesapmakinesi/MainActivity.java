package com.iremse.hesapmakinesi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView sonuc;
    TextView islemler; // Yapılan işlemleri gösterecek TextView
    String currentNumber = "";
    String operation = "";
    double result = 0;
    String calculationHistory = "";
    boolean hasDecimal = false; // Ondalık noktanın kullanılıp kullanılmadığını anlamak için

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sonuc = findViewById(R.id.sonuc);
        islemler = findViewById(R.id.islemler);
    }

    public void click(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "AC":
                currentNumber = "";
                operation = "";
                result = 0;
                sonuc.setText("");
                islemler.setText(""); // Temizleme işlemi
                hasDecimal = false; // Sıfırlama işlemi sonrasında ondalık sıfırla
                break;
            case "+":
            case "-":
            case "x":
            case "÷":
                operation = buttonText;
                if (!currentNumber.equals("")) {
                    result = Double.parseDouble(currentNumber);
                }
                currentNumber = "";
                calculationHistory += result + operation;
                islemler.setText(calculationHistory); // İşlem adımlarını güncelle
                hasDecimal = false; // Yeni bir işlem başlatıldığında ondalığı sıfırla
                break;
            case "=":
                if (!currentNumber.equals("")) {
                    performOperation(Double.parseDouble(currentNumber));
                }
                sonuc.setText(String.valueOf(result));
                currentNumber = "";
                calculationHistory = "";
                islemler.setText("");
                hasDecimal = false; // İşlem sonrasında ondalığı sıfırla
                break;
            case "%":
                if (!currentNumber.equals("")) {
                    double num = Double.parseDouble(currentNumber);
                    double percent = num / 100;
                    sonuc.setText(String.valueOf(percent));
                    currentNumber = String.valueOf(percent);
                }
                break;
            case "←":
                if (!currentNumber.isEmpty()) {
                    currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
                }
                sonuc.setText(currentNumber);
                break;
            case ".":
                if (!hasDecimal) { // Ondalğı kontrol et
                    currentNumber += buttonText;
                    sonuc.setText(currentNumber);
                    hasDecimal = true; // Ondalık noktanın kullanıldığını işaretle
                }
                break;
            default:
                currentNumber += buttonText;
                sonuc.setText(currentNumber);
                break;
        }
    }

    private void performOperation(double num) {
        switch (operation) {
            case "+":
                result += num;
                break;
            case "-":
                result -= num;
                break;
            case "x":
                result *= num;
                break;
            case "÷":
                if (num != 0) {
                    result /= num;
                } else {
                    result = 0;
                }
                break;
        }
    }
}
