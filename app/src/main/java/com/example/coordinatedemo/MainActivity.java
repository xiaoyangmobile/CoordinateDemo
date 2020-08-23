package com.example.coordinatedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CallBack {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        TouchView view = findViewById(R.id.vw);
        view.setCallBack(this);
    }

    @Override
    public void response(String msg) {
        tv.setText(msg);
    }
}